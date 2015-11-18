package servico;

import dao.AluguelDao;
import dao.AluguelDaoBd;
import java.util.List;
import model.Aluguel;

/**
 * * Classe CONTROLLER para a classe Aluguel
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 *
 */

public class AluguelServico {
    /**
     * Método booleano que se comunica com a classe AluguelDao para verificar se o código do aluguel de um livro existe no banco de dados;
     * @param id - recebe o ID de um livro;
     * @return retorna 'true' ou 'false';
     */
    public boolean CodigoExiste(int id) {
        AluguelDao dao = new AluguelDaoBd();
        Aluguel aluguel = dao.procurarPorId(id);
        if (aluguel != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que comunica a classe AluguelDao da inserção de um novo aluguel;
     * @param a - recebe um objeto aluguel de um livro;
     */
    public void addAluguel(Aluguel a) {
        new AluguelDaoBd().inserir(a);
    }

    /**
     * Método que se comunica com a classe AluguelDao para listar os alugueis de livros existentes;
     * @return retorna a  lista de alugueis do banco de dados;
     */
    public List<Aluguel> listarAluguel() {
        return (new AluguelDaoBd().listar());
    }

    /**
     * Método que solicita ao AluguelDao que busque as informações de um aluguel pelo código do mesmo;
     * @param id - recebe o ID de um aluguel;
     * @return retorna o objeto aluguel encontrado no banco de dados;
     */
    public Aluguel buscarPorCodigo(int id) {
        AluguelDao dao = new AluguelDaoBd();
        Aluguel aluguel = dao.procurarPorId(id);
        return aluguel;
    }
    
}
