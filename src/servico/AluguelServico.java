package servico;

import dao.AluguelDao;
import dao.AluguelDaoBd;
import java.util.List;
import model.Aluguel;

public class AluguelServico {

    public boolean CodigoExiste(int id) {
        AluguelDao dao = new AluguelDaoBd();
        Aluguel aluguel = dao.procurarPorId(id);
        if (aluguel != null) {
            return true;
        } else {
            return false;
        }
    }

    public void addAluguel(Aluguel a) {
        new AluguelDaoBd().inserir(a);
    }

    public List<Aluguel> listarAluguel() {
        return (new AluguelDaoBd().listar());
    }
    
    public Aluguel buscarPorCodigo(int id) {
        AluguelDao dao = new AluguelDaoBd();
        Aluguel aluguel = dao.procurarPorId(id);
        return aluguel;
    }

}
