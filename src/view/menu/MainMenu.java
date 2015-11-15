package view.menu;

public class MainMenu {

    public static final int OP_CLIENTE = 1;
    public static final int OP_LIVRO = 2;
    public static final int OP_CONSULTA = 3;
//    public static final int OP_AGENDA = 4;
//    public static final int OP_HISTORICO = 5;
    public static final int OP_SAIR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Menu Cliente\n"
                + "2- Menu Livro\n"
                + "3- Menu Consulta\n"
//                + "4- Menu Agenda\n"
//                + "5- Menu Histórico\n"
                + "0- Sair da Aplicação"
                + "\n--------------------------------------");
    }
}