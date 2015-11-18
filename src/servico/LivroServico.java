package servico;

import dao.LivroDao;
import dao.LivroDaoBd;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Livro;

/**
 * * Classe CONTROLLER para a classe Livro
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 *
 */

public class LivroServico {
    /**
     * Método booleano que se comunica com a classe LivroDao para verificar, pelo título do livro, se ele existe no banco de dados;
     * @param titulo - recebe o título do livro;
     * @return retorna 'true' ou 'false';
     */
    public boolean LivroExiste(String titulo) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorTitulo(titulo);
        return (livro != null);
    }

    /**
     * Método booleano que se comunica com a classe LivroDao para verificar, pelo ISBN do livro, se ele existe no banco de dados;
     * @param isbn - recebe o ISBN do livro;
     * @return retorna 'true' ou 'false'
     */
    public boolean LivroExiste(long isbn) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorIsbn(isbn);
        if (livro != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que comunica a classe LivroDao da inserção de um novo livro;
     * @param l - recebe um objeto Livro;
     */
    public void addLivro(Livro l) {
        new LivroDaoBd().inserir(l);
    }

    /**
     * Método que se comunica com a classe CLivroDao para listar os livros existentes;
     * @return retorna a lista de livros no banco de dados;
     */
    public List<Livro> listarLivros() {
        return (new LivroDaoBd().listar());
    }

    /**
     *Método que solicita ao LivroDao que busque as informações de um livro pelo ISBN do mesmo;
     * @param isbn - recebe o ISBN do livro;
     * @return retorna o objeto Livro encontrado no banco de dados;
     */
    public Livro buscarLivroPorIsbn(long isbn) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorIsbn(isbn);
        return (livro);
    }

    /**
     * Método que solicita ao LivroDao que busque as informações de um livro pelo título do mesmo;
     * @param titulo - recebe o título do livro;
     * @return retorna o objeto Livro encontrado no banco de dados;
     */
    public Livro buscarLivroPorTitulo(String titulo) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorTitulo(titulo);
        return (livro);
    }

    /**
     * Método que solicita ao LivroDao que edite as informações de variável tipo long de um livro;
     * @param op - recebe o número do operador que indica as opções do menu;
     * @param novoX - recebe o novo valor a ser editado;
     * @param l - recebe o objeto Livro que terá algum valor alterado;
     */
    public void editarLivro(String op, long novoX, Livro l) {
        LivroDao dao = new LivroDaoBd();
        if (op.equals("1")) {
            dao.editar(l, novoX, "isbn");
        }
    }

    /**
     * Método que solicita ao LivroDao que edite as informações de variável tipo String de um livro;
     * @param op - recebe o número do operador que indica as opções do menu;
     * @param novoX - recebe o novo valor a ser editado;
     * @param l - recebe o objeto Livro que terá algum valor alterado;
     */
    public void editarLivro(String op, String novoX, Livro l) {
        LivroDao dao = new LivroDaoBd();
        if (op.equals("2")) {
            dao.editar(l, novoX, "titulo");
        }
        if (op.equals("3")) {
            dao.editar(l, novoX, "editora");
        }
        if (op.equals("4")) {
            dao.editar(l, novoX, "autor");
        }
        if (op.equals("5")) {
            dao.editar(l, novoX, "anoPublicacao");
        }
    }

    /**
     * Método que solicita ao LivroDao que delete um livro do banco de dados;
     * @param l - recebe o objeto Livro;
     */
    public void deletarLivro(Livro l) {
        LivroDao dao = new LivroDaoBd();
        dao.deletar(l);
    }

    /**
     * Método que solicita ao LivroDao que verifica se um ISBN existe;
     * @param isbn - recebe o ISBN de um livro;
     * @return retorna 'true' ou 'false'
     */
    public boolean verificaIsbn(long isbn) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorIsbn(isbn);
        if (livro != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para não deixar que um número de ISBN negativo seja inserido;
     * @param isbnDigitado - recebe o ISBN digitado;
     * @return retorna 'true' ou 'false'
     */
    public boolean validacaoIsbn(long isbnDigitado) {
        if (isbnDigitado >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que permite a visualização de uma lista de livros disponíveis;
     */
    public void VisualizarLivroDisponivel() {
        List<Livro> listaLivroTemp = new LivroDaoBd().listar();
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "Isbn: ") + "\t"
                + String.format("%-20s", "|Titulo: ") + "\t"
                + String.format("%-20s", "|Editora: ") + "\t"
                + String.format("%-20s", "|Autor(es): ") + "\t"
                + String.format("%-20s", "|Ano de Publicação: ") + "\t"
                + String.format("%-20s", "|Quantidade de vezes que foi alugado: "));
        for (Livro listaLivro : listaLivroTemp) {
            if (listaLivro.isDisponibilidade() == true) {
                System.out.println(String.format("%-10s", listaLivro.getIsbn()) + "\t"
                    + String.format("%-20s", "|" + listaLivro.getTitulo()) + "\t"
                    + String.format("%-20s", "|" + listaLivro.getEditora()) + "\t"
                    + String.format("%-20s", "|" + listaLivro.getAutor()) + "\t"
                    + String.format("%-20s", "|" + listaLivro.getAnoPublicacao()) + "\t"
                    + String.format("%-20s", "|" + listaLivro.getQntdeTotalAlugado()));
            }
        }
    }


    /**
     * Método booleano para validar se o ano informado é válido;
     * @param campo - recebe a String campo (do ano);
     * @return retorna se o campo do ano contém caracateres válidos;
     */
    public boolean validarAnoPublicacao(String campo) {
        return campo.matches("[0-9]{4}+");
    }

    /**
     * Método que permite a visualização de uma lista de livros mais retirados;
     */
    public void livrosMaisRetirados() {
        List<Livro> listaLivroTemp = new LivroDaoBd().listar();
        Collections.sort(listaLivroTemp, new livroCompRetirados());
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "Isbn: ") + "\t"
                + String.format("%-20s", "|Titulo: ") + "\t"
                + String.format("%-20s", "|Editora: ") + "\t"
                + String.format("%-20s", "|Autor(es): ") + "\t"
                + String.format("%-20s", "|Ano de Publicação: ") + "\t"
                + String.format("%-20s", "|Quantidade de vezes que foi alugado: "));
        for (Livro listaLivro : listaLivroTemp) {
            System.out.println(String.format("%-10s", listaLivro.getIsbn()) + "\t"
                    + String.format("%-20s", "|" + listaLivro.getTitulo()) + "\t"
                    + String.format("%-20s", "|" + listaLivro.getEditora()) + "\t"
                    + String.format("%-20s", "|" + listaLivro.getAutor()) + "\t"
                    + String.format("%-20s", "|" + listaLivro.getAnoPublicacao()) + "\t"
                    + String.format("%-20s", "|" + listaLivro.getQntdeTotalAlugado()));
        }
    }

    /**
     * Inner class para comparação de objetos do tipo Livros com a quantidade de livros retirados e implementa a interface Comparator;
     */
    public class livroCompRetirados implements Comparator<Livro> {

        @Override
        public int compare(Livro l1, Livro l2) {
            return l2.getQntdeTotalAlugado() - l1.getQntdeTotalAlugado();
        }
    }

}
