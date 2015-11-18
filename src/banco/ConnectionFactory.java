package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Classe responsável pela conexão;
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public class ConnectionFactory {

    private final static String HOST = "localhost";
    private final static String PORT = "5432";
    private final static String BD = "ProjetoBibliotecaBD";
    private final static String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + BD;
    private final static String USUARIO = "postgres";
    private final static String SENHA = "senac2015";
/**
 * Método para realizar conexão com o banco de dados;
 * @return retorna conexão estabelecida;
 */
    public static Connection getConnection() {
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (conexao);
    }

}
