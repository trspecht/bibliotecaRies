package view.menu;

public class AluguelMenu {
    public static final String OP_ALUGARLIVRO = "1";
    public static final String OP_LISTARALUGUEL = "2";
    public static final String OP_VOLTAR = "0";

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Alugar Livro\n"
                + "2- Listar Alguel\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }    
}