package view;

import java.util.InputMismatchException;
import model.Cliente;
import servico.ClienteServico;
import util.Console;
import view.menu.ClienteMenu;
/**
 * Classe VIEW da classe Cliente
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 *
 */
public class ClienteUI {

    private final ClienteServico servicoC = new ClienteServico();

    public ClienteUI() {
    }

    /**
     * Método que executa o menu do Cliente;
     */
    public void executar() {
        String op = "";
        while (!op.equals("0")) {
            op = Console.scanString(ClienteMenu.getOpcoes() + "\nDigite sua opção:");
            switch (op) {
                case ClienteMenu.OP_CADASTRAR:
                    cadastrarCliente();
                    break;
                case ClienteMenu.OP_EDITAR:
                    editarCliente();
                    break;
                case ClienteMenu.OP_DELETAR:
                    deletarCliente();
                    break;
                case ClienteMenu.OP_LISTAR:
                    mostrarCliente();
                    break;
                case ClienteMenu.OP_PROCURARCLIENTE:
                    procurarCliente();
                    break;
                case ClienteMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");
            }
        }
    }

    /**
     * Método intermediário para cadastrar um novo cliente;
     */
    private void cadastrarCliente() {
        try {
            long rg = Console.scanLong("RG: ");
            while (servicoC.validacaoRG(rg) == false) {
                rg = Console.scanLong("Número não pode ser negativo, digite novamente: ");
            }
            servicoC.clienteExiste(rg);
            if (servicoC.clienteExiste(rg) == true) {
                System.out.println("RG já existente no nosso sistema");
                return;
            }
            String nome = Console.scanString("Nome e Sobrenome: ");
            while (!servicoC.validacaoNome(nome) || nome == null) {
                nome = Console.scanString("Nome inválido, digite novamente:");
            }
            String telefone = Console.scanString("Telefone (xx) xxxx-xxxx: ");
            while (servicoC.isTelefone(telefone) != true || telefone == null) {
                telefone = Console.scanString("Número de telefone inválido, digite novamente:");
            }
            long matricula;
            do {
                matricula = servicoC.gerarMatricula();
            } while (servicoC.matriculaExiste(matricula) == true);
            servicoC.addCliente(new Cliente(nome, rg, telefone, matricula));
            System.out.println("Cliente " + nome + " cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida!");
        }
    }

    /**
     * Método para mostrar os dados de todos os clientes cadastrados;
     */
    public void mostrarCliente() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "RG") + "\t"
                + String.format("%-20s", "|Nome") + "\t"
                + String.format("%-20s", "|Telefone")
                + String.format("%-20s", "|Matrícula")
                + String.format("%-20s", "|Livros em posse")
                + String.format("%-20s", "|Quantidade de livros alugados")
                + String.format("%-20s", "    |Quantidade de atrasos"));
        for (Cliente cliente : servicoC.listarClientes()) {
            System.out.println(String.format("%-10s", cliente.getRg()) + "\t"
                    + String.format("%-20s", "|" + cliente.getNome()) + "\t"
                    + String.format("%-20s", "|" + cliente.getTelefone() + "\t"
                            + String.format("%-20s", "    |" + cliente.getMatricula() + "\t"
                                    + String.format("%-20s", "|" + cliente.getLivrosAlugados() + "\t"
                                            + String.format("%-20s", "            |" + cliente.getQntdelivrosalugados()
                                                    + String.format("%-20s", "                                |" + cliente.getQntdeatraso()))))));
        }
    }

    /**
     * Método intermediário para editar os dados de um cliente;
     */
    private void editarCliente() {
        try {
            long rg = Console.scanLong("RG do cliente a ser editado: ");
            while (servicoC.validacaoRG(rg) == false) {
                rg = Console.scanLong("Número não pode ser negativo, digite novamente: ");
            }
            if (servicoC.clienteExiste(rg) == false) {
                System.out.println("RG não existente no cadastro");
                return;
            }
            Cliente cli = servicoC.buscarClientePorRg(rg);
            System.out.println(cli);
            String op = Console.scanString("O que deseja alterar? \n1- Nome e Sobrenome: \n2- Telefone: \n3- RG: \n");
            switch (op) {
                case "1": {
                    String novoNome = Console.scanString("Digite o novo nome e sobrenome: ");
                    while (!servicoC.validacaoNome(novoNome) || novoNome == null) {
                        novoNome = Console.scanString("Nome inválido, digite novamente: ");
                    }
                    servicoC.editarCliente(op, novoNome, cli);
                    System.out.println("Nome alterado com sucesso!");
                    break;
                }
                case "2": {
                    String novoTelefone = Console.scanString("Digite o novo Telefone (xx) xxxx-xxxx: ");
                    while (servicoC.isTelefone(novoTelefone) != true || novoTelefone == null) {
                        novoTelefone = Console.scanString("Número de telefone inválido, digite novamente: ");
                    }
                    servicoC.editarCliente(op, novoTelefone, cli);
                    System.out.println("Telefone alterado com sucesso!");
                    break;
                }
                case "3": {
                    long novoRg = Console.scanLong("Digite o novo RG: ");
                    while (servicoC.validacaoRG(novoRg) == false) {
                        novoRg = Console.scanLong("Número não pode ser negativo, digite novamente: ");
                    }
                    while (servicoC.clienteExiste(novoRg) == true) {
                        novoRg = Console.scanLong("RG já existe no sistema, digite novamente: ");
                    }
                    servicoC.editarCliente(op, novoRg, cli);
                    System.out.println("RG alterado com sucesso!");
                    break;
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

    /**
     * Método intermediário para deletar um cliente;
     */
    private void deletarCliente() {
        try {
            long rg = Console.scanLong("RG do paciente a ser deletado: ");
            while (servicoC.validacaoRG(rg) == false) {
                rg = Console.scanLong("Número não pode ser negativo, digite novamente: ");
            }
            if (!servicoC.clienteExiste(rg)) {
                System.out.println("RG não existente no cadastro");
                return;
            }
            Cliente cli = servicoC.buscarClientePorRg(rg);
            System.out.println("Informações do Cliente:");
            System.out.println("ID: " + cli.getId());
            System.out.println("RG: " + cli.getRg());
            System.out.println("Nome: " + cli.getRg());
            System.out.println("Telefone: " + cli.getTelefone());
            System.out.println("Matrícula: " + cli.getMatricula());
            System.out.println("Total de livros alugados: " + cli.getQntdelivrosalugados());

            String confirmacao = Console.scanString("Deseja realmente remover o cliente "
                    + cli.getNome() + "? (Sim/Não)");
            if (confirmacao.equalsIgnoreCase("sim")) {
                if (cli.getLivrosAlugados() > 0) {
                    System.out.println("Você deve entregar todos os livros alugados primeiro!");
                } else {
                    servicoC.deletarCliente(cli);
                    System.out.println("Cliente " + cli.getNome() + " deletado com sucesso!");
                }
            } else {
                System.out.println("Operação cancelada!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida!");
        }
    }

    /**
     * Método intermediário para procurar um cliente já existente;
     */
    private void procurarCliente() {
        String op = Console.scanString("Como deseja efetuar a busca do cliente? \n1- Procurar por RG \n2- Procurar por Nome \n3- Voltar \n");
        try {
            switch (op) {
                case "1": {
                    long novoRg = Console.scanLong("Digite o RG: ");
                    while (servicoC.validacaoRG(novoRg) == false) {
                        novoRg = Console.scanLong("Número não pode ser negativo, digite novamente: ");
                    }
                    if (servicoC.clienteExiste(novoRg) == false) {
                        System.out.println("Cliente não cadastrado!");
                        return;
                    }
                    Cliente cli = servicoC.buscarClientePorRg(novoRg);
                    System.out.println(cli);
                    break;
                }
                case "2": {
                    String novoNome = Console.scanString("Digite o ID do cliente: ");
                    if (servicoC.nomeExiste(novoNome) == false) {
                        System.out.println("Cliente não cadastrado!");
                        return;
                    }
                    Cliente cli = servicoC.buscarClientePorNome(novoNome);
                    System.out.println(cli);
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
