
package dao;

import java.util.List;
import model.Devolucao;


public interface DevolucaoDao {
    public void inserir(Devolucao devolucao);
    public List<Devolucao> listar();
    public Devolucao procurarPorCodigo(long codigo);
    public Devolucao buscarPorCodigo(long codigo);

    
    
}
