import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertPessoa
{
    public static void main(String[] args)
    {

        String url = "jdbc:mysql://localhost:3306/java_teste";
        String usuario = "root";
        String senha = "";

        try
        {
            // Cria a conexão
            Connection conexao = DriverManager.getConnection(url, usuario, senha);

            // Cria um objeto Statement que permite executar comandos SQL no banco
            Statement stmt = conexao.createStatement();

            // -----------------------------------------------------------------------------

            // Comando SQL que será executado no banco de dados (INSERT na tabela pessoas)
            String sql = "INSERT INTO pessoas (nome, idade) VALUES ('Ana', 22)";

            // Executa o comando SQL no banco
            stmt.executeUpdate(sql);

            // Mensagem de confirmação
            System.out.println("Pessoa inserida com sucesso!");

            // Fecha o Banco
            conexao.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}