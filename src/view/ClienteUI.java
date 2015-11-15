package view;

import model.Cliente;
import servico.ClienteServico;
import util.Console;
import view.menu.ClienteMenu;

public class ClienteUI {

    private ClienteServico servicoC;

    public ClienteUI() {
        servicoC = new ClienteServico();
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(ClienteMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
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
                case ClienteMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != ClienteMenu.OP_VOLTAR);
    }

    private void cadastrarCliente() {
        long rg = Console.scanLong("RG: ");
        servicoC.clienteExiste(rg);
        if (servicoC.clienteExiste(rg) == true) {
            System.out.println("RG já existente no nosso sistema");
            return;
        }
        String nome = Console.scanString("Nome e Sobrenome: ");
        while (!servicoC.validacaoNome(nome)) {
            nome = Console.scanString("Nome inválido, digite novamente:");
        }
        String telefone = Console.scanString("Telefone (xx) xxxx-xxxx: ");
        while (servicoC.isTelefone(telefone) != true) {
            telefone = Console.scanString("Número de telefone inválido, digite novamente:");
        }
        long matricula;
        do {
            matricula = servicoC.gerarMatricula();
        } while (servicoC.matriculaExiste(matricula) == true);
        servicoC.addCliente(new Cliente(nome, rg, telefone, matricula));
        System.out.println("Cliente " + nome + " cadastrado com sucesso!");
    }

    public void mostrarCliente() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "RG") + "\t"
                + String.format("%-20s", "|Nome") + "\t"
                + String.format("%-20s", "|Telefone")
                + String.format("%-20s", "|Matrícula")
                + String.format("%-20s", "|Quantidade de livros alugados"));
        for (Cliente cliente : servicoC.listarClientes()) {
            System.out.println(String.format("%-10s", cliente.getRg()) + "\t"
                    + String.format("%-20s", "|" + cliente.getNome()) + "\t"
                    + String.format("%-20s", "|" + cliente.getTelefone() + "\t"
                    + String.format("%-20s", "    |" + cliente.getMatricula() + "\t"
                    + String.format("%-20s", "|" + cliente.getQntdelivrosalugados()))));
        }
    }

    private void editarCliente() {
        long rg = Console.scanLong("RG do cliente a ser editado: ");
        if (servicoC.clienteExiste(rg) == false) {
            System.out.println("RG não existente no cadastro");
            return;
        }
        Cliente cli = servicoC.buscarClientePorRg(rg);
        String nome = Console.scanString("Nome e Sobrenome (Atual: " + cli.getNome() + "):");
        while (!servicoC.validacaoNome(nome)) {
            nome = Console.scanString("Nome inválido, digite novamente:");
        }
        String telefone = Console.scanString("Telefone (xx) xxxx-xxxx (Atual: " + cli.getTelefone() + "):");
        while (servicoC.isTelefone(telefone) != true) {
            telefone = Console.scanString("Número de telefone inválido, digite novamente:");
        }
        rg = Console.scanLong("RG (Atual: " + cli.getRg() + "):");
        cli.setRg(rg);
        cli.setNome(nome);
        cli.setTelefone(telefone);
        servicoC.editarCliente(cli);
        System.out.println("Cliente " + nome + " alterado com sucesso!");
    }

    private void deletarCliente() {
        long rg = Console.scanLong("RG do paciente a ser deletado: ");
        if (!servicoC.clienteExiste(rg)) {
            System.out.println("RG não existente no cadastro");
            return;
        }
        Cliente cli = servicoC.buscarClientePorRg(rg);
        System.out.println("Informações do Cliente:");
        System.out.println("RG: " + cli.getRg());
        System.out.println("Nome: " + cli.getRg());
        System.out.println("Telefone: " + cli.getTelefone());
        System.out.println("Matrícula: " + cli.getMatricula());
                
        String confirmacao = Console.scanString("Deseja realmente remover o cliente "
                + cli.getNome() + "? (Sim/Não)");
        if (confirmacao.equalsIgnoreCase("sim")) {
            if(cli.getLivrosAlugados() > 0){
                System.out.println("Você deve entregar todos os livros alugados primeiro!");
            } else {
            servicoC.deletarCliente(cli);
            System.out.println("Cliente " + cli.getNome() + " deletado com sucesso!");
            }
        } else {
            System.out.println("Operação cancelada!");
        }
    }

}
