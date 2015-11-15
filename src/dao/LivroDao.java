package dao;

import java.util.List;
import model.Livro;


public interface LivroDao {
    public void inserir(Livro livro);
    public void deletar(Livro livro);
    public void editar(Livro livro, long novoX, String coluna);
    public void editar(Livro livro, String novoX, String coluna);
    public List<Livro> listar();
    public Livro procurarPorIsbn(long isbn);
    public Livro procurarPorTitulo(String titulo);
    public List<Livro> listarPorTitulo(String titulo);    
}