package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory
{
    // Configuração de conexão com o banco de dados
    private static final String url = "jdbc:mysql://localhost:3306/cartao_ponto";
    private static final String usuario = "root";
    private static final String senha = "";

    // Método que pega a conexão do banco de dados
    public static Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(url, usuario, senha);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Erro ao conectar ao banco de dados!", e);
        }
    }

}