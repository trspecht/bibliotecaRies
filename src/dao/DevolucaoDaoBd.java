package dao;

import banco.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Devolucao;

public class DevolucaoDaoBd implements DevolucaoDao {

    private Connection conexao;
    private PreparedStatement comando;

    //Metodo inserir alterado para trabalhar com data e receber o id auto 
    //increment e já inserir no objeto paciente (recebido por parâmetro)
    //Caso queira retornar, só retornar id.
    
    @Override
    public void inserir(Devolucao devolucao) {
        int id = 0;
        try {
            String sql = "INSERT INTO devolucao (codigo, data, id, cod) "
                    + "VALUES (?,?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setLong(1, devolucao.getCodigo());
            //Trabalhando com data: lembrando dataUtil -> dataSql
            java.sql.Date dataSql = new java.sql.Date(devolucao.getDataAluguel().getTime());
            comando.setDate(2, dataSql);
            comando.setInt(3, devolucao.getC().getId());
            comando.setInt(4, devolucao.getLivrosAlugados().getCod());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                devolucao.setId(id);
            }
            atualizarCliente(devolucao);
            atualizarLivro(devolucao);
            atualizarQntLivro(devolucao);
            atualizarQntLivrosAlugadosCliente(devolucao);

        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        //Caso queira retornar id:
        //return (id);
    }

    @Override
    public void atualizarCliente(Devolucao devolucao) {
        try {
            String sql = "UPDATE cliente SET livrosAlugados=(livrosAlugados-1) "
                    + "WHERE id=?";

            conectar(sql);
            comando.setInt(1, devolucao.getC().getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AluguelDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizarLivro(Devolucao devolucao) {
        try {
            String sql = "UPDATE livro SET disponibilidade=true "
                    + "WHERE cod=?";
            conectar(sql);
            comando.setInt(1, devolucao.getLivrosAlugados().getCod());
            comando.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AluguelDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Devolucao> listar() {
        List<Devolucao> listaDevolucao = new ArrayList<>();
        String sql = "SELECT * FROM devolucao ORDER BY codigo";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int idAluguel = resultado.getInt("idAluguel");
                long codigo = resultado.getLong("codigo");
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSql = resultado.getDate("dataAluguel");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
                int id = resultado.getInt("id");
                int cod = resultado.getInt("cod");

                Devolucao devolucao = new Devolucao(idAluguel, codigo, dataUtil, id, codigo);
                listaDevolucao.add(devolucao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }
        return (listaDevolucao);
    }

    @Override
    public Devolucao procurarPorCodigo(long codigo) {
        String sql = "SELECT * FROM aluguel WHERE codigo = ?";

        try {
            conectar(sql);
            comando.setLong(1, codigo);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int idAluguel = resultado.getInt("idAluguel");
                long codigoX = resultado.getLong("codigo");
                //Trabalhando com data: lembrando dataSql -> dataUtil
                java.sql.Date dataSql = resultado.getDate("dataAluguel");
                java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
                int id = resultado.getInt("id");
                int cod = resultado.getInt("cod");

                Aluguel aluguel = new Aluguel(idAluguel, codigoX, dataUtil, id, codigo);
                return aluguel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    public void conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
    }

    public void conectarObtendoId(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    public void fecharConexao() {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDaoBd.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
