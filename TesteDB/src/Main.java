import java.sql.Connection;
import java.sql.DriverManager;

public class Main
{
    public static void main(String[] args)
    {
        // Configuração de conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/java_teste";
        String usuario = "root";
        String senha = "";

        try
        {
            // Cria a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection(url, usuario, senha);

            // Mensagem para confirmar a conexão
            System.out.println("Conectado ao MySQL com sucesso!");

            // Fecha a conexão com o banco
            conexao.close();
        }
        catch (Exception e)
        {
            // Mensagem para mostrar se houve algum erro de conexão
            System.out.println("Erro ao conectar no banco");

            // Mostra detalhes do erro no console
            e.printStackTrace();
        }

    }
}