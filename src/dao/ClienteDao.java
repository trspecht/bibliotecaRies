
package dao;

import java.util.List;
import model.Cliente;


public interface ClienteDao {
    public void inserir(Cliente cliente);
    public void deletar(Cliente cliente);
    public void atualizar(Cliente cliente);
    public List<Cliente> listar();
    public Cliente procurarPorId(int id);
    public Cliente procurarPorRg(long rg);
    public Cliente procurarPorMatricula(long matricula);
    public List<Cliente> listarPorNome(String nome);
    
}