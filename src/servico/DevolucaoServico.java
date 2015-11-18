package servico;

import dao.DevolucaoDao;
import dao.DevolucaoDaoBd;
import java.util.Date;
import java.util.List;
import model.Aluguel;
import model.Devolucao;

/**
 * * Classe CONTROLLER para a classe Devolução
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 *
 */
public class DevolucaoServico {
    /**
     * Método que comunica a classe DevolucaoDao da inserção de uma devolução;
     * @param d - recebe um objeto Devolucao;
     * @param atrasado - recebe um boolean 'true' ou 'false' da devolução do livro;
     */
    public void addDevolucao(Devolucao d, boolean atrasado) {
        new DevolucaoDaoBd().inserir(d, atrasado);
    }

    /**
     * Método que se comunica com a classe DevolucaoDao para listar as devoluções;
     * @return retorna a lista de devoluções do banco de dados;
     */
    public List<Devolucao> listarDevolucao() {
        return (new DevolucaoDaoBd().listar());
    }

    /**
     * Método que solicita ao DevolucaoDao que busque, por código, a devolução de um livro;
     * @param id - recebe o ID de uma devolução;
     * @return retorna o objeto Devolucao encontrado no banco de dados;
     */
    public Devolucao buscarPorCodigo(int id) {
        DevolucaoDao dao = new DevolucaoDaoBd();
        Devolucao devolucao = dao.procurarPorId(id);
        return devolucao;
    }

    /**
     * Método que calcula a diferença de duas datas;
     * @param aluguel - recebe a data do aluguel;
     * @param date - recebe a data da devolução;
     * @return
     */
    public int difDatas(Aluguel aluguel, Date date) {
        long tempo1 = aluguel.getDataAluguel().getTime();
        long tempo2 = date.getTime();
        long difTempo = tempo2 - tempo1;
        return (int) ((difTempo + 60L * 60 * 1000) / (24L * 60 * 60 * 1000)) + 1;
    }

}