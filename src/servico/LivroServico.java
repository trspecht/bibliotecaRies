package servico;

import dao.LivroDao;
import dao.LivroDaoBd;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Livro;

public class LivroServico {

    public boolean LivroExiste(String titulo) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorTitulo(titulo);
        return (livro != null);
    }

    public boolean LivroExiste(long isbn) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorIsbn(isbn);
        if (livro != null) {
            return true;
        } else {
            return false;
        }
    }

    public void addLivro(Livro l) {
        new LivroDaoBd().inserir(l);
    }

    public List<Livro> listarLivros() {
        return (new LivroDaoBd().listar());
    }

    public Livro buscarLivroPorIsbn(long isbn) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorIsbn(isbn);
        return (livro);
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorTitulo(titulo);
        return (livro);
    }

    public void editarLivro(String op, long novoX, Livro l) {
        LivroDao dao = new LivroDaoBd();
        if (op.equals("1")) {
            dao.editar(l, novoX, "isbn");
        }
    }

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

    public void deletarLivro(Livro l) {
        LivroDao dao = new LivroDaoBd();
        dao.deletar(l);
    }

    public boolean verificaIsbn(long isbn) {
        LivroDao dao = new LivroDaoBd();
        Livro livro = dao.procurarPorIsbn(isbn);
        if (livro != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validacaoIsbn(long isbnDigitado) {
        if (isbnDigitado >= 0) {
            return true;
        } else {
            return false;
        }
    }
    
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

    
        
        
    public boolean validarAnoPublicacao(String campo) {
        return campo.matches("[0-9]{4}+");
    }

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
    
    public class livroCompRetirados implements Comparator<Livro> {

        @Override
        public int compare(Livro l1, Livro l2) {
            return l2.getQntdeTotalAlugado() - l1.getQntdeTotalAlugado();
        }
    }

}
