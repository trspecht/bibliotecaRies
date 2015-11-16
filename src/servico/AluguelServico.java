package servico;

import dao.AluguelDao;
import dao.AluguelDaoBd;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import model.Aluguel;

public class AluguelServico {

    public boolean CodigoExiste(long codigo) {
        AluguelDao dao = new AluguelDaoBd();
        Aluguel aluguel = dao.procurarPorCodigo(codigo);
        if (aluguel != null) {
            return true;
        } else {
            return false;
        }
    }

    public long randomCodigo() {
        Date n = new Date();
        long codigoNumero = n.getTime();
        return codigoNumero;
    }

    public long gerarCodigo() {
        long codigo = randomCodigo();
        return codigo;
    }

    public void addAluguel(Aluguel a) {
        new AluguelDaoBd().inserir(a);
    }

    public List<Aluguel> listarAluguel() {
        return (new AluguelDaoBd().listar());
    }
    
    public Aluguel buscarPorCodigo(long codigo) {
        AluguelDao dao = new AluguelDaoBd();
        Aluguel aluguel = dao.procurarPorCodigo(codigo);
        return aluguel;
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
