package ConexaoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = ""; //VC DEVE INSERIR SUAS INFORMAÇÕES AQUI
    private static final String USUARIO = ""; //VC DEVE INSERIR SUAS INFORMAÇÕES AQUI
    private static final String SENHA = ""; //VC DEVE INSERIR SUAS INFORMAÇÕES AQUI

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
