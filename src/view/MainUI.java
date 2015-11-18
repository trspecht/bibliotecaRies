package view;

import util.Console;
import view.menu.MainMenu;
/**
 * Classe VIEW da classe Main
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 *
 */
public class MainUI {

    public MainUI() {
    }

    /**
     * Método para executar o menu principal;
     */
    public void executar() {
        String op = "";
        while (!op.equals("0")) {
            op = Console.scanString(MainMenu.getOpcoes() + "\nDigite sua opção:");
            switch (op) {
                case MainMenu.OP_CLIENTE:
                    new ClienteUI().executar();
                    break;
                case MainMenu.OP_LIVRO:
                    new LivroUI().executar();
                    break;
                case MainMenu.OP_ALUGUEL:
                    new AluguelUI().executar();
                    break;
                case MainMenu.OP_DEVOLUCAO:
                    new DevolucaoUI().executar();
                    break;
                case MainMenu.OP_CONSULTA:
                    new ConsultaUI().executar();
                    break;
                case MainMenu.OP_SAIR:
                    System.out.println("Aplicação finalizada!!!");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        }
    }

}
