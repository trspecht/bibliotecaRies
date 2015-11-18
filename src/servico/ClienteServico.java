package servico;

import dao.ClienteDao;
import dao.ClienteDaoBd;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import model.Cliente;


/**
 * * Classe CONTROLLER para a classe Cliente
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 *
 */

public class ClienteServico {

    /**
     * Método booleano que se comunica com a classe ClienteDao para verificar, pelo número do RG, se um cliente existe no banco de dados;
     * @param rg - recebe o RG do cliente;
     * @return retorna 'true' ou 'false';
     */
    public boolean clienteExiste(long rg) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorRg(rg);
        if (cliente != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método booleano que se comunica com a classe ClienteDao para verificar, pelo número de matrícula, se um cliente existe no banco de dados;
     * @param matricula - recebe o número da matrícula do cliente;
     * @return retorna 'true' ou 'false';
     */
    public boolean matriculaExiste(long matricula) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorMatricula(matricula);
        if (cliente != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método booleano que se comunica com a classe ClienteDao para verificar, pelo nome do cliente, se ele existe no banco de dados;
     * @param nome - recebe o nome do cliente;
     * @return retorna 'true' ou 'false';
     */
    public boolean nomeExiste(String nome) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorNome(nome);
        if (cliente != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para randomizar e gerar um número de matrícula para o cliente, baseado na data do sistema;
     * @return retorna número de matrícula gerado;
     */
    public long gerarMatricula() {
        Date n = new Date();
        long matriculaNumero = n.getTime();
        return matriculaNumero;
    }

//    public long gerarMatricula() {
//        long matricula = randomMatricula();
//        return matricula;
//    }

    /**
     * Método que comunica a classe ClienteDao da inserção de um novo cliente;
     * @param c - recebe um objeto cliente;
     */
    public void addCliente(Cliente c) {
        new ClienteDaoBd().inserir(c);
    }

    /**
     * Método que se comunica com a classe ClienteDao para listar os clientes existentes;
     * @return retorna a lista de clientes no banco de dados;
     */
    public List<Cliente> listarClientes() {
        return (new ClienteDaoBd().listar());
    }

    /**
     * Método que solicita ao ClienteDao que busque as informações de um cliente pelo RG do mesmo;
     * @param rg - recebe o RG de um cliente;
     * @return retorna o objeto Cliente encontrado no banco de dados;
     */
    public Cliente buscarClientePorRg(long rg) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorRg(rg);
        return (cliente);
    }

    /**
     * Método que solicita ao ClienteDao que busque as informações de um cliente pelo nome do mesmo;
     * @param nome - recebe o nome de um cliente;
     * @return retorna o objeto Cliente encontrado no banco de dados;
     */
    public Cliente buscarClientePorNome(String nome) {
        ClienteDao dao = new ClienteDaoBd();
        Cliente cliente = dao.procurarPorNome(nome);
        return (cliente);
    }

    /**
     * Método que solicita ao ClienteDao que edite as informações de variável tipo String de um cliente;
     * @param op - recebe o número do operador que indica as opções do menu;
     * @param novoValor - recebe o novo valor a ser editado;
     * @param c - recebe o objeto cliente que terá algum valor alterado;
     */
    public void editarCliente(String op, String novoValor, Cliente c) {
        ClienteDao dao = new ClienteDaoBd();
        if (op.equals("1")) {
            dao.editar(c, novoValor, "nome");
        }
        if (op.equals("2")) {
            dao.editar(c, novoValor, "telefone");
        }
    }

    /**
     * Método que solicita ao ClienteDao que edite as informações de variável tipo long de um cliente;
     * @param op - recebe o número do operador que indica as opções do menu;
     * @param novoValor - recebe o novo valor a ser editado;
     * @param c - recebe o objeto cliente que terá algum valor alterado;
     */
    public void editarCliente(String op, long novoValor, Cliente c) {
        ClienteDao dao = new ClienteDaoBd();
        if (op.equals("3")) {
            dao.editar(c, novoValor, "rg");
        }
    }

    /**
     * Método que solicita ao ClienteDao que delete um cliente do banco de dados;
     * @param c - recebe o cliente;
     */
    public void deletarCliente(Cliente c) {
        ClienteDao dao = new ClienteDaoBd();
        dao.deletar(c);
    }

    /**
     * Método que solicita ao ClienteDao que valide se há espaço entre nome e sobrenome de um cliente;
     * @param nomeDigitado - recebe o nome digitado;
     * @return retorna 'true' ou 'false';
     */
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

<<<<<<< HEAD
    /**
     * Método booleano para verificar se a String digitada é um número de telefone válido;
     * @param novoTelefone - recebe o número de telefone;
     * @return retorna se o número de telefone é válido;
     */
=======
    public boolean validacaoRG(long rgDigitado) {
        if (rgDigitado >= 0) {
            return true;
        } else {
            return false;
        }
    }

>>>>>>> d18cb5ffccb6fb1d23c5c680620f6b651abdb668
    public boolean isTelefone(String novoTelefone) {
        return novoTelefone.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}")
                || novoTelefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}");
    }

    /**
     * Método que ordena clientes que mais alugam livros;
     */
    public void clientesQueMaisAlugam() {
        List<Cliente> listaClienteTemp = new ClienteDaoBd().listar();
        Collections.sort(listaClienteTemp, new ClienteCompAluguel());
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "RG") + "\t"
                + String.format("%-20s", "|Nome") + "\t"
                + String.format("%-20s", "|Telefone")
                + String.format("%-20s", "|Matrícula")
                + String.format("%-20s", "|Livros em posse")
                + String.format("%-20s", "|Quantidade de livros alugados")
                + String.format("%-20s", "    |Quantidade de atrasos"));
        for (Cliente listaClientes : listaClienteTemp) {
            System.out.println(String.format("%-10s", listaClientes.getRg()) + "\t"
                    + String.format("%-20s", "|" + listaClientes.getNome()) + "\t"
                    + String.format("%-20s", "|" + listaClientes.getTelefone() + "\t"
                            + String.format("%-20s", "    |" + listaClientes.getMatricula() + "\t"
                                    + String.format("%-20s", "|" + listaClientes.getLivrosAlugados() + "\t"
                                            + String.format("%-20s", "            |" + listaClientes.getQntdelivrosalugados()
                                                    + String.format("%-20s", "                                |" + listaClientes.getQntdeatraso()))))));
        }
    }

    /**
     * Método que ordena clientes que mais atrasam a entrega dos livros;
     */
    public void clientesQueMaisAtrasam() {
        List<Cliente> listaClienteTemp = new ClienteDaoBd().listar();
        Collections.sort(listaClienteTemp, new ClienteCompAtraso());
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "RG") + "\t"
                + String.format("%-20s", "|Nome") + "\t"
                + String.format("%-20s", "|Telefone")
                + String.format("%-20s", "|Matrícula")
                + String.format("%-20s", "|Livros em posse")
                + String.format("%-20s", "|Quantidade de livros alugados")
                + String.format("%-20s", "    |Quantidade de atrasos"));
        for (Cliente listaClientes : listaClienteTemp) {
            System.out.println(String.format("%-10s", listaClientes.getRg()) + "\t"
                    + String.format("%-20s", "|" + listaClientes.getNome()) + "\t"
                    + String.format("%-20s", "|" + listaClientes.getTelefone() + "\t"
                            + String.format("%-20s", "    |" + listaClientes.getMatricula() + "\t"
                                    + String.format("%-20s", "|" + listaClientes.getLivrosAlugados() + "\t"
                                            + String.format("%-20s", "            |" + listaClientes.getQntdelivrosalugados()
                                                    + String.format("%-20s", "                                |" + listaClientes.getQntdeatraso()))))));
        }
    }

    /**
     * Inner class para comparação de objetos do tipo cliente com a quantidade de livros alugados e implementa a interface Comparator;
     */
    public class ClienteCompAluguel implements Comparator<Cliente> {

        @Override
        public int compare(Cliente c1, Cliente c2) {
            return c2.getQntdelivrosalugados() - c1.getQntdelivrosalugados();
        }
    }

    /**
     * Inner class para comparação de objetos do tipo cliente com a quantidade de atrasos e implementa a interface Comparator;
     */
    public class ClienteCompAtraso implements Comparator<Cliente> {

        @Override
        public int compare(Cliente c1, Cliente c2) {
            return c2.getQntdeatraso() - c1.getQntdeatraso();
        }
    }

}
