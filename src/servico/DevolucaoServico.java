package servico;

import dao.DevolucaoDao;
import dao.DevolucaoDaoBd;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import model.Aluguel;
import model.Devolucao;

public class DevolucaoServico {

    public void addDevolucao(Devolucao d) {
        new DevolucaoDaoBd().inserir(d);
    }

    public List<Devolucao> listarDevolucao() {
        return (new DevolucaoDaoBd().listar());
    }

    public Devolucao buscarPorCodigo(long codigo) {
        DevolucaoDao dao = new DevolucaoDaoBd();
        Devolucao devolucao = dao.procurarPorCodigo(codigo);
        return devolucao;
    }

    public int difDatas(Aluguel aluguel, Date date) {
        long tempo1 = aluguel.getDataAluguel().getTime();
        long tempo2 = date.getTime();
        long difTempo = tempo2 - tempo1;
        return (int) ((difTempo + 60L * 60 * 1000) / (24L * 60 * 60 * 1000)) + 1;
    }

}
//    public void clientesQueMaisAlugam() {
//        List<Cliente> listaClienteTemp = new ClienteDaoBd().listar();
//        Collections.sort(listaClienteTemp, new ClienteCompAluguel());
//        for (Cliente listaClientes : listaClienteTemp) {
//            System.out.println("Nome: " + listaClientes.getNome() + " Matrícula: " + listaClientes.getMatricula()
//                    + " Quantidade de Livros alugados: " + listaClientes.getQntdelivrosalugados());
//        }
//    }
//
//    public class AluguelCompAluguel implements Comparator<Aluguel> {
//
//        @Override
//        public int compare(Aluguel a1, Aluguel a2) {
//            return a2.getQntdelivrosalugados() - a1.getQntdelivrosalugados();
//        }
//    }
//}
