/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.InputMismatchException;
import model.Livro;
import servico.LivroServico;
import util.Console;
import view.menu.LivroMenu;

public class LivroUI {

    private final LivroServico servicoL = new LivroServico();

    public LivroUI() {
    }

    public void executar() {
        String op = "";
        while (!op.equals("0")) {
            op = Console.scanString(LivroMenu.getOpcoes() + "\nDigite sua opção:");
            switch (op) {
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
                case LivroMenu.OP_PROCURARLIVRO:
                    procurarLivro();
                    break;
                case LivroMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");
            }
        }
    }

    private void cadastrarLivro() {
        try {
            long isbn = Console.scanLong("Isbn: ");
            servicoL.LivroExiste(isbn);
            if (servicoL.LivroExiste(isbn) == true) {
                System.out.println("Isbn já existe no cadastro");
                return;
            }
            String titulo = Console.scanString("Titulo: ");
            while (titulo == null || titulo.trim().length() == 0) {
                titulo = Console.scanString("Titulo inválido, digite novamente: ");
            }
            String editora = Console.scanString("Editora: ");
            while (editora == null || editora.trim().length() == 0) {
                editora = Console.scanString("Editora inválida, digite novamente: ");
            }
            String autor = Console.scanString("Autor(es): ");
            while (autor == null || autor.trim().length() == 0) {
                autor = Console.scanString("Autor(es) inválido, digite novamente: ");
            }
            String anoPublicacao = Console.scanString("Ano de Publicação ex: xxxx: ");
            while (servicoL.validarAnoPublicacao(anoPublicacao) != true || anoPublicacao == null) {
                anoPublicacao = Console.scanString("Ano de publicação inválido, digite novamente: ");
            }

            servicoL.addLivro(new Livro(isbn, titulo, editora, autor, anoPublicacao));
            System.out.println("Livro " + titulo + " cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida!");
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
        try {
            long isbn = Console.scanLong("Isbn do livro a ser editado: ");
            if (servicoL.LivroExiste(isbn) == false) {
                System.out.println("Livro não existe no cadastro");
                return;
            }
            Livro livro = servicoL.buscarLivroPorIsbn(isbn);
            System.out.println(livro);
            String op = Console.scanString("O que deseja alterar? \n1- Isbn: \n2- Titulo: \n3- Editora: \n4- Autor(es): \n5- Ano de Publicação: \n6- Voltar: \n");
            switch (op) {
                case "1": {
                    long novoIsbn = Console.scanLong("Digite o novo Isbn: ");
                    while (servicoL.LivroExiste(novoIsbn) == true) {
                        novoIsbn = Console.scanLong("Isbn já existe em nosso sistema, favor verifique e digite novamente: ");
                    }
                    servicoL.editarLivro(op, novoIsbn, livro);
                    System.out.println("Isbn alterado com sucesso!");
                    break;
                }
                case "2": {
                    String novoTitulo = Console.scanString("Digite o novo Titulo: ");
                    while (novoTitulo == null || novoTitulo.trim().length() == 0) {
                        novoTitulo = Console.scanString("Titulo inválido, digite novamente: ");
                    }
                    servicoL.editarLivro(op, novoTitulo, livro);
                    System.out.println("Titulo alterado com sucesso!");
                    break;
                }
                case "3": {
                    String novaEditora = Console.scanString("Digite a nova editora: ");
                    while (novaEditora == null || novaEditora.trim().length() == 0) {
                        novaEditora = Console.scanString("Editora inválida, digite novamente: ");
                    }
                    servicoL.editarLivro(op, novaEditora, livro);
                    System.out.println("Editora alterada com sucesso!");
                    break;
                }
                case "4": {
                    String novoAutor = Console.scanString("Digite o novo Autor: ");
                    while (novoAutor == null || novoAutor.trim().length() == 0) {
                        novoAutor = Console.scanString("Autor(es) inválido, digite novamente: ");
                    }
                    servicoL.editarLivro(op, novoAutor, livro);
                    System.out.println("Autor(es) alterado com sucesso!");
                    break;
                }
                case "5": {
                    String novoAnoPublicacao = Console.scanString("Digite o novo ano de publicação xxxx: ");
                    while (servicoL.validarAnoPublicacao(novoAnoPublicacao) != true || novoAnoPublicacao == null) {
                        novoAnoPublicacao = Console.scanString("Ano de publicação inválido, digite novamente: ");
                    }
                    servicoL.editarLivro(op, novoAnoPublicacao, livro);
                    System.out.println("Ano de publicação alterado com sucesso!");
                    break;
                }
                case "6": {
                    return;
                }
                default: {
                    System.out.println("Opção inválida!");
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida!");
        }
    }

    private void deletarLivro() {
        try {
            long isbn = Console.scanLong("Isbn do livro a ser deletado: ");
            if (!servicoL.LivroExiste(isbn)) {
                System.out.println("Livro não existe no cadastro");
            } else {
                Livro livro = servicoL.buscarLivroPorIsbn(isbn);
                System.out.println("Informações do Livro:");
                System.out.println("Código: " + livro.getCod());
                System.out.println("Isbn: " + livro.getIsbn());
                System.out.println("Titulo: " + livro.getTitulo());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("Autor(es): " + livro.getAutor());
                System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
                System.out.println("Quantidade de vezes que foi alugado: " + livro.getQntdeTotalAlugado());

                String confirmacao = Console.scanString("Deseja realmente remover o livro "
                        + livro.getTitulo() + "? (Sim/Não)");
                if (confirmacao.equalsIgnoreCase("sim")) {
                    if (livro.isDisponibilidade() == false) {
                        System.out.println("Livro está alugado, esperar efetuarem a entrega para excluir do sistema!");
                    } else {
                        servicoL.deletarLivro(livro);
                        System.out.println("Livro " + livro.getTitulo() + " deletado com sucesso!");
                    }
                } else {
                    System.out.println("Operação cancelada!");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida!");
        }
    }

    private void procurarLivro() {
        String op = Console.scanString("Como deseja efetuar a busca do livro? \n1- Procurar por Isbn \n2- Procurar por Titulo \n3- Voltar \n");
        try {
            switch (op) {
                case "1": {
                    long novoIsbn = Console.scanLong("Digite o Isbn do livro: ");
                    if (servicoL.LivroExiste(novoIsbn) == false) {
                        System.out.println("Livro não cadastrado!");
                        return;
                    }
                    Livro livro = servicoL.buscarLivroPorIsbn(novoIsbn);
                    System.out.println(livro);
                    break;
                }
                case "2": {
                    String novoTitulo = Console.scanString("Digite o titulo do livro: ");
                    if (servicoL.LivroExiste(novoTitulo) == false) {
                        System.out.println("Livro não cadastrado!");
                        return;
                    }
                    Livro livro = servicoL.buscarLivroPorTitulo(novoTitulo);
                    System.out.println(livro);
                    break;
                }
                case "3": {
                    return;
                }
                default: {
                    System.out.println("Opção inválida!");
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida!");
        }
    }

}
