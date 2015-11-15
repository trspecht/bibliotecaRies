/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

public class LivroMenu {

    public static final int OP_CADASTRAR = 1;
    public static final int OP_EDITAR = 2;
    public static final int OP_DELETAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_PROCURARLIVRO = 5;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar Livro\n"
                + "2- Editar Livro\n"
                + "3- Deletar Livro\n"
                + "4- Listar Livro\n"
                + "5- Procurar Livro\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }
}
