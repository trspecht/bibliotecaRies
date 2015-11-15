package dao;

import java.util.List;
import model.Livro;


public interface LivroDao {
    public void inserir(Livro livro);
    public void deletar(Livro livro);
    public void atualizar(Livro livro);
    public List<Livro> listar();
    public Livro procurarPorIsbn(long isbn);
    public Livro procurarPorTitulo(String titulo);
    public List<Livro> listarPorTitulo(String titulo);    
}