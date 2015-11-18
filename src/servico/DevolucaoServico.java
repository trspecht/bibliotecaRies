package servico;

import dao.DevolucaoDao;
import dao.DevolucaoDaoBd;
import java.util.Date;
import java.util.List;
import model.Aluguel;
import model.Devolucao;

public class DevolucaoServico {

    public void addDevolucao(Devolucao d, boolean atrasado) {
        new DevolucaoDaoBd().inserir(d, atrasado);
    }

    public List<Devolucao> listarDevolucao() {
        return (new DevolucaoDaoBd().listar());
    }

    public Devolucao buscarPorCodigo(int id) {
        DevolucaoDao dao = new DevolucaoDaoBd();
        Devolucao devolucao = dao.procurarPorId(id);
        return devolucao;
    }

    public int difDatas(Aluguel aluguel, Date date) {
        long tempo1 = aluguel.getDataAluguel().getTime();
        long tempo2 = date.getTime();
        long difTempo = tempo2 - tempo1;
        return (int) ((difTempo + 60L * 60 * 1000) / (24L * 60 * 60 * 1000)) + 1;
    }

}