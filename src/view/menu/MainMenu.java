package view.menu;

public class MainMenu {

    public static final int OP_CLIENTE = 1;
    public static final int OP_LIVRO = 2;
    public static final int OP_ALUGUEL = 3;
    public static final int OP_DEVOLUCAO = 4;
//    public static final int OP_HISTORICO = 5;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Menu Cliente\n"
                + "2- Menu Livro\n"
                + "3- Menu Alugar Livro\n"
                + "4- Menu Devolução de Livro\n"
//                + "5- Menu Histórico\n"
                + "0- Sair da Aplicação"
                + "\n--------------------------------------");
    }
}