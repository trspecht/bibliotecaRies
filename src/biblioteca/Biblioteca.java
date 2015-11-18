package biblioteca;

import view.MainUI;

/**
 * Classe responsável por executar o Main.UI;
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public class Biblioteca {

    /**
     * Método para chamar o MainUI, que exibe todos os menus principais.
     */
    public static void main(String[] args) {
        new MainUI().executar();
    }
    
}
