package view;

import util.Console;
import view.menu.MainMenu;

public class MainUI {

    public MainUI() {
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(MainMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case MainMenu.OP_CLIENTE:
                    new ClienteUI().executar();
                    break;
                case MainMenu.OP_LIVRO:
                    new LivroUI().executar();
                    break;
                case MainMenu.OP_CONSULTA:
                   // new ConsultaUI().executar();
                    break;
//                case MainMenu.OP_AGENDA:
//                    new AgendaUI(listaPacientes,agenda).executar();
//                    break;
//                case MainMenu.OP_HISTORICO:
//                    new HistoricoUI(listaConsultas).executar();
//                    break;
                case MainMenu.OP_SAIR:
                    System.out.println("Aplicação finalizada!!!");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != MainMenu.OP_SAIR);
    }

}

