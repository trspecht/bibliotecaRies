package view.menu;
/**
 * Classe que gera o MENU Aluguel;
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 *
 */

public class AluguelMenu {
    public static final String OP_ALUGARLIVRO = "1";
    public static final String OP_LISTARALUGUEL = "2";
    public static final String OP_VOLTAR = "0";

    /**
     * Método que mostra o menu;
     * @return retorna as opções do menu;
     */

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Alugar Livro\n"
                + "2- Listar Alguel\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }    
}