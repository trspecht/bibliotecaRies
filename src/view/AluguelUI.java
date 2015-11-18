package view;

import java.util.Date;
import java.util.InputMismatchException;
import model.Aluguel;
import model.Cliente;
import model.Livro;
import servico.AluguelServico;
import servico.ClienteServico;
import servico.LivroServico;
import util.Console;
import util.DateUtil;
import view.menu.AluguelMenu;

public class AluguelUI {

    private AluguelServico servicoA;
    private ClienteServico servicoC;
    private LivroServico servicoL;

    public AluguelUI() {
        servicoA = new AluguelServico();
        servicoC = new ClienteServico();
        servicoL = new LivroServico();
    }

    public void executar() {
        String op = "";
        while (!op.equals("0")) {
            op = Console.scanString(AluguelMenu.getOpcoes() + "\nDigite sua opção:");
            switch (op) {
                case AluguelMenu.OP_ALUGARLIVRO:
                    alugarLivro();
                    break;
                case AluguelMenu.OP_LISTARALUGUEL:
                    mostrarAluguel();
                    break;    
                case AluguelMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    return;
                default:
                    System.out.println("Opção inválida..");
            }
        }
    }

    private void alugarLivro() {
        try {
            long rg = Console.scanLong("Digite seu RG para continuar: ");
            servicoC.clienteExiste(rg);
            if (servicoC.clienteExiste(rg) == false) {
                System.out.println("Não existe cadastro em no nosso sistema, faça seu cadastro para poder alugar um livro!");
                return;
            }
            Cliente cli = servicoC.buscarClientePorRg(rg);
            if (cli.getLivrosAlugados() == 3) {
                System.out.println(cli.getNome() + " você já possui o máximo de livros alugados, favor entregue algum livro para poder alugar!");
                return;
            }
            String op = Console.scanString("Como deseja efetuar a busca do livro? \n1- Procurar por Isbn \n2- Procurar por Titulo \n3- Voltar \n");
            switch (op) {
                case "1": {
                    long novoIsbn = Console.scanLong("Digite o Isbn do livro: ");
                    if (servicoL.LivroExiste(novoIsbn) == false) {
                        System.out.println("Livro não cadastrado!");
                        return;
                    }
                    Livro livro = servicoL.buscarLivroPorIsbn(novoIsbn);
                    System.out.println(livro);
                    String confirmacao = Console.scanString("Deseja realmente alugar o livro "
                            + livro.getTitulo() + "? (Sim/Não)");
                    if (confirmacao.equalsIgnoreCase("sim")) {
                        if (livro.isDisponibilidade() == false) {
                            System.out.println("Livro está alugado, esperar efetuarem a entrega no dia "
                                    + " para alugar!");
                        } else {
                            Date dateFormat = new Date();
                            java.sql.Date dataSql;
                            dateFormat = new java.sql.Date(dateFormat.getTime());
                            dataSql = (java.sql.Date) dateFormat;

                            servicoA.addAluguel(new Aluguel(dateFormat, cli, livro));
                            System.out.println("Livro " + livro.getTitulo() + " alugado com sucesso! ");
                        }
                    } else {
                        System.out.println("Operação cancelada!");
                    }
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
                    String confirmacao = Console.scanString("Deseja realmente alugar o livro "
                            + livro.getTitulo() + "? (Sim/Não)");
                    if (confirmacao.equalsIgnoreCase("sim")) {
                        if (livro.isDisponibilidade() == false) {
                            System.out.println("Livro está alugado, esperar efetuarem a entrega para alugar!");
                        } else {
                            Date dateFormat = new Date();
                            java.sql.Date dataSql;
                            dateFormat = new java.sql.Date(dateFormat.getTime());
                            dataSql = (java.sql.Date) dateFormat;

                            servicoA.addAluguel(new Aluguel(dateFormat, cli, livro));
                            System.out.println("Livro " + livro.getTitulo() + " alugado com sucesso! ");
                        }
                    } else {
                        System.out.println("Operação cancelada!");
                    }
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
            System.out.println("Entrada inválida");
        }
    }

    public void mostrarAluguel() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-20s", "|Código do Aluguel") + "\t"
                + String.format("%-20s", "|Nome do cliente")
                + String.format("%-20s", "  |Titulo do livro alugado")
                + String.format("%-20s", "    |Data do aluguel:"));
        for (Aluguel aluguel : servicoA.listarAluguel()) {
            System.out.println(String.format("%-10s", aluguel.getId()) + "\t"
                    + String.format("%-20s", "        |" + aluguel.getC().getNome()) + "\t"
                    + String.format("%-20s", "      |" + aluguel.getLivrosAlugados().getTitulo() + "\t"
                            + String.format("%-20s", "                  |" + DateUtil.dateToString(aluguel.getDataAluguel()))));
        }
    }

}
