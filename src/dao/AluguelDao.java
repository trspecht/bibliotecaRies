
package dao;

import java.util.List;
import model.Aluguel;

public interface AluguelDao {
    public void inserir(Aluguel aluguel);
    public void atualizarCliente(Aluguel aluguel);
    public void atualizarLivro(Aluguel aluguel);
    public void atualizarQntLivro(Aluguel aluguel);
    public void atualizarQntLivrosAlugadosCliente(Aluguel aluguel);
    public List<Aluguel> listar();
    public Aluguel procurarPorId(int id);

    
    
}
