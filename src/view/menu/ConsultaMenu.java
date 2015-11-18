package view.menu;

/**
 * Classe que gera o MENU Consulta;
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 *
 */

public class ConsultaMenu {
    public static final String OP_LISTARLIVROS = "1";
    public static final String OP_LISTARLIVROSDISPONIVEIS = "2";
    public static final String OP_LISTARLIVROSMAISRETIRADOS = "3";
    public static final String OP_LISTARCLIENTESQUEMAISRETIRAMLIVROS = "4";
    public static final String OP_LISTARCLIENTESQUEMAISATRASARAM = "5";
    public static final String OP_VOLTAR = "0";

    /**
     * Método que mostra o menu de Consultas;
     * @return retorna as opções do menu;
     */

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Listar todos os livros\n"
                + "2- Listar livros disponíveis\n"
                + "3- Listar livros mais alugados\n"
                + "4- Listar clientes que mais alugaram\n"
                + "5- Listar clientes que mais atrasaram na entrega do livro\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }    
}