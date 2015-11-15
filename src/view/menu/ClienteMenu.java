package view.menu;

public class ClienteMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_EDITAR = 2;
    public static final int OP_DELETAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_PROCURARCLIENTE = 5;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Cliente\n"
                + "2- Editar Cliente\n"
                + "3- Deletar Cliente\n"
                + "4- Listar Cliente\n"
                + "5- Procurar Cliente\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }    
}