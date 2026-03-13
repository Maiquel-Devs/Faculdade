package dao;

import factory.ConnectionFactory;
import model.Departamento;
import model.Funcionario;
import model.RegistroPonto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroPontoDAO
{
    // Método para buscar os registros de um funcionário em uma data específica
    public List<RegistroPonto> buscarPorFuncionarioEData(int idFuncionario, LocalDate data)
    {
        // Variável que guarda todas as informações da função (Método)
        List<RegistroPonto> registros = new ArrayList<>();

        // SQL que busca o ponto, o funcionário e o departamento dele
        String sql = "SELECT r.id, r.data_hora, f.nome_completo, d.nome as depto_nome " +
                "FROM registro_ponto r " +
                "INNER JOIN funcionario f ON r.id_funcionario = f.id " +
                "INNER JOIN departamento d ON f.id_departamento = d.id " +
                "WHERE r.id_funcionario = ? AND DATE(r.data_hora) = ? " +
                "ORDER BY r.data_hora ASC";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql))
        {
            stmt.setInt(1, idFuncionario);
            stmt.setString(2, data.toString());

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                // Criando os objetos responsável por organizar dados
                Departamento depto = new Departamento();
                depto.setNome(rs.getString("depto_nome"));

                Funcionario func = new Funcionario();
                func.setNomeCompleto(rs.getString("nome_completo"));
                func.setDepartamento(depto);    // Chave Estrangeira

                RegistroPonto registro = new RegistroPonto();
                registro.setId(rs.getInt("id"));
                registro.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                registro.setFuncionario(func);  // Chave Estrangeira

                registros.add(registro);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return registros;
    }

}