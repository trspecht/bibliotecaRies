package servico;

import dao.ClienteDao;
import dao.ClienteDaoBd;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import model.Cliente;

public class ClienteServico {

    public boolean clienteExiste(long rg) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorRg(rg);
        if (cliente != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean matriculaExiste(long matricula) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorMatricula(matricula);
        if (cliente != null) {
            return true;
        } else {
            return false;
        }
    }

    public long randomMatricula() {
        Date n = new Date();
        long matriculaNumero = n.getTime();
        return matriculaNumero;
    }

    public long gerarMatricula() {
        long matricula = randomMatricula();
        return matricula;
    }

    public void addCliente(Cliente c) {
        new ClienteDaoBd().inserir(c);
    }

    public List<Cliente> listarClientes() {
        return (new ClienteDaoBd().listar());
    }

    public Cliente buscarClientePorRg(long rg) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorRg(rg);
        return (cliente);
    }

    public void editarCliente(Cliente c) {
        ClienteDao dao = new ClienteDaoBd();
        dao.atualizar(c);
    }

    public void deletarCliente(Cliente c) {
        ClienteDao dao = new ClienteDaoBd();
        dao.deletar(c);
    }

    public boolean validacaoNome(String nomeDigitado) {
        nomeDigitado = nomeDigitado.trim();
        boolean espacos = false;

        for (int i = 0; i < nomeDigitado.length(); i++) {
            char carac = nomeDigitado.charAt(i);
            if (Character.isLetter(carac) || Character.isWhitespace(carac)) {
                if (Character.isWhitespace(carac)) {
                    espacos = true;
                }
            } else {
                return false;
            }
        }
        return espacos == true;
    }

    public boolean isTelefone(String novoTelefone) {
        return novoTelefone.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}")
                || novoTelefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}");
    }

    public void clientesQueMaisAlugam() {
        List<Cliente> listaClienteTemp = new ClienteDaoBd().listar();
        Collections.sort(listaClienteTemp, new ClienteCompAluguel());
        for (Cliente listaClientes : listaClienteTemp) {
            System.out.println("Nome: " + listaClientes.getNome() + " Matrícula: " + listaClientes.getMatricula()
                    + " Quantidade de Livros alugados: " + listaClientes.getQntdelivrosalugados());
        }
    }

    public class ClienteCompAluguel implements Comparator<Cliente> {

        @Override
        public int compare(Cliente c1, Cliente c2) {
            return c2.getQntdelivrosalugados() - c1.getQntdelivrosalugados();
        }
    }
}
