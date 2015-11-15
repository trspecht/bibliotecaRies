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

    public void editarLivro(Livro l) {
        LivroDao dao = new LivroDaoBd();
        dao.atualizar(l);
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

    public void VisualizarLivroDisponivel() {
        List<Livro> listaLivroTemp = new LivroDaoBd().listar();
        for (Livro listaLivro : listaLivroTemp) {
            if (listaLivro.isDisponibilidade() == true) {
                System.out.println(listaLivro);
            }
        }
    }

    public boolean validarAnoPublicacao(String campo) {
        return campo.matches("[0-9]{4}+");
    }
    
    public void livrosMaisRetirados() {
        List<Livro> listaLivroTemp = new LivroDaoBd().listar();
        Collections.sort(listaLivroTemp, new livroCompRetirados());
        for (Livro listaLivro : listaLivroTemp) {
            System.out.println("Isbn: " + listaLivro.getIsbn() + " \nTitulo: " + listaLivro.getTitulo()
                    + " \nEditora: " + listaLivro.getEditora() + " \nAutor(es): " + listaLivro.getAutor()
                    + " \nAno de Publicação: " + listaLivro.getAnoPublicacao() + " \nQuantidade de vezes que foi alugado: "
                    + listaLivro.getQntdeTotalAlugado());
        }

    }

    public class livroCompRetirados implements Comparator<Livro> {

        @Override
        public int compare(Livro l1, Livro l2) {
            return l2.getQntdeTotalAlugado() - l1.getQntdeTotalAlugado();
        }
    }

}
