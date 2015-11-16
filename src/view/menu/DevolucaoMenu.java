package view.menu;

public class DevolucaoMenu {
    public static final String OP_DEVOLVERLIVRO = "1";
    public static final String OP_VOLTAR = "0";

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Devolver Livro\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }    
}