
package dao;

import java.util.List;
import model.Devolucao;


public interface DevolucaoDao {
    public void inserir(Devolucao devolucao, boolean atrasado);
    public void atualizarCliente(Devolucao devolucao);
    public void atualizarLivro(Devolucao devolucao);
    public List<Devolucao> listar();
    public Devolucao procurarPorId(int id);
    public void atualizarAtrasoCliente(Devolucao devolucao);
  
    
}
