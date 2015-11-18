package view.menu;

/**
 * Classe que gera o MENU Principal
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 *
 */

public class MainMenu {

    public static final String OP_CLIENTE = "1";
    public static final String OP_LIVRO = "2";
    public static final String OP_ALUGUEL = "3";
    public static final String OP_DEVOLUCAO = "4";
    public static final String OP_CONSULTA = "5";
    public static final String OP_SAIR = "0";

    /**
     * Método que mostra o menu;
     * @return retorna as opções do menu;
     */

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Menu Cliente\n"
                + "2- Menu Livro\n"
                + "3- Menu Alugar Livro\n"
                + "4- Menu Devolução de Livro\n"
                + "5- Menu Consulta\n"
                + "0- Sair da Aplicação"
                + "\n--------------------------------------");
    }
}