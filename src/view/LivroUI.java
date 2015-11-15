/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Livro;
import servico.LivroServico;
import util.Console;
import view.menu.LivroMenu;

public class LivroUI {

    private LivroServico servicoL;

    public LivroUI() {
        this.servicoL = new LivroServico();
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(LivroMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case LivroMenu.OP_CADASTRAR:
                    cadastrarLivro();
                    break;
                case LivroMenu.OP_EDITAR:
                    editarLivro();
                    break;
                case LivroMenu.OP_DELETAR:
                    deletarLivro();
                    break;
                case LivroMenu.OP_LISTAR:
                    mostrarLivro();
                    break;
                case LivroMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != LivroMenu.OP_VOLTAR);
    }

    private void cadastrarLivro() {
        long isbn = Console.scanLong("Isbn: ");
        servicoL.LivroExiste(isbn);
        if (servicoL.LivroExiste(isbn) == true) {
            System.out.println("Isbn já existe no cadastro");
            return;
        } else {
            String titulo = Console.scanString("Titulo: ");
            String editora = Console.scanString("Editora: ");
            String autor = Console.scanString("Autor(es): ");
            String anoPublicacao = Console.scanString("Ano de Publicação ex: xxxx: ");
            while (servicoL.validarAnoPublicacao(anoPublicacao) != true) {
                anoPublicacao = Console.scanString("Ano de publicação inválido, digite novamente: ");
            }
            boolean disponibilidade = true;
            int qntdeTotalAlugado = 0;

            servicoL.addLivro(new Livro(isbn, titulo, editora, autor, anoPublicacao, disponibilidade, qntdeTotalAlugado));
            System.out.println("Livro " + titulo + " cadastrado com sucesso!");
        }
    }

    public void mostrarLivro() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "Isbn: ") + "\t"
                + String.format("%-20s", "|Titulo: ") + "\t"
                + String.format("%-20s", "|Editora: ") + "\t"
                + String.format("%-20s", "|Autor(es): ") + "\t"
                + String.format("%-20s", "|Ano de Publicação: ") + "\t"
                + String.format("%-20s", "|Quantidade de vezes que foi alugado: "));
        for (Livro livro : servicoL.listarLivros()) {
            System.out.println(String.format("%-10s", livro.getIsbn()) + "\t"
                    + String.format("%-20s", "|" + livro.getTitulo()) + "\t"
                    + String.format("%-20s", "|" + livro.getEditora()) + "\t"
                    + String.format("%-20s", "|" + livro.getAutor()) + "\t"
                    + String.format("%-20s", "|" + livro.getAnoPublicacao()) + "\t"
                    + String.format("%-20s", "|" + livro.getQntdeTotalAlugado()));
        }

    }

    private void editarLivro() {
        long isbn = Console.scanLong("Isbn do livro a ser editado: ");
        if (!servicoL.LivroExiste(isbn)) {
            System.out.println("Livro não existe no cadastro");
        } else {
            Livro livro = servicoL.buscarLivroPorIsbn(isbn);
            isbn = Console.scanLong("Isbn (Atual: " + livro.getIsbn() + "):");
            while (servicoL.LivroExiste(isbn)) {
                isbn = Console.scanLong("Isbn já existe em nosso sistema, favor verifique o número e digite novamente: ");
            }
            String titulo = Console.scanString("Titulo (Atual: " + livro.getTitulo() + "):");
            String editora = Console.scanString("Editora (Atual: " + livro.getEditora() + "):");
            String autor = Console.scanString("Autor(es) (Atual: " + livro.getAutor() + "):");
            String anoPublicacao = Console.scanString("Ano de Publicação ex: xxxx: (Atual: " + livro.getAnoPublicacao() + "):");
            while (servicoL.validarAnoPublicacao(anoPublicacao) != true) {
                anoPublicacao = Console.scanString("Ano de publicação inválido, digite novamente: ");
            }           
            livro.setIsbn(isbn);
            livro.setTitulo(titulo);
            livro.setEditora(editora);
            livro.setAutor(autor);
            livro.setAnoPublicacao(anoPublicacao);
            servicoL.editarLivro(livro);
            System.out.println("Livro " + titulo + " alterado com sucesso!");
        }

    }

    private void deletarLivro() {
        long isbn = Console.scanLong("Isbn do livro a ser deletado: ");
        if (!servicoL.LivroExiste(isbn)) {
            System.out.println("Livro não existe no cadastro");
        } else {
            Livro livro = servicoL.buscarLivroPorIsbn(isbn);
            System.out.println("Informações do Livro:");
            System.out.println("Isbn: " + livro.getIsbn());
            System.out.println("Titulo: " + livro.getTitulo());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("Autor(es): " + livro.getAutor());
            System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
            System.out.println("Quantidade de vezes que foi alugado: " + livro.getQntdeTotalAlugado());

            String confirmacao = Console.scanString("Deseja realmente remover o livro "
                    + livro.getTitulo() + "? (sim/nao)");
            if (confirmacao.equalsIgnoreCase("sim")) {
                servicoL.deletarLivro(livro);
                System.out.println("Livro " + livro.getTitulo() + " deletado com sucesso!");
            } else {
                System.out.println("Operação cancelada!");
            }

        }
    }
}
