
package dao;

import java.util.List;
import model.Cliente;


public interface ClienteDao {
    public void inserir(Cliente cliente);
    public void deletar(Cliente cliente);
    public void editar(Cliente cliente, String novoX, String coluna);
    public void editar(Cliente cliente, long novoX, String coluna);
    public List<Cliente> listar();
    public Cliente procurarPorId(int id);
    public Cliente procurarPorRg(long rg);
    public Cliente procurarPorNome(String nome);
    public Cliente procurarPorMatricula(long matricula);
    public List<Cliente> listarPorNome(String nome);
    
}