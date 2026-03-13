import dao.RegistroPontoDAO;
import model.RegistroPonto;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        // DEFINIÇÃO DOS PARÂMETROS DE BUSCA (funcionario e dia)
        int idFuncionario = 3;
        LocalDate dataConsulta = LocalDate.of(2026, 3, 10);

        RegistroPontoDAO dao = new RegistroPontoDAO();
        List<RegistroPonto> registros = dao.buscarPorFuncionarioEData(idFuncionario, dataConsulta);

        if (registros.isEmpty())
        {
            System.out.println("Nenhum registro encontrado para esta data.");
            return;
        }

        // Cabeçalho do Relatório
        RegistroPonto primeiro = registros.get(0);
        System.out.println("RELATÓRIO DE PONTO");
        System.out.println("Funcionário: " + primeiro.getFuncionario().getNomeCompleto());
        System.out.println("Departamento: " + primeiro.getFuncionario().getDepartamento().getNome());
        System.out.println("Data: " + dataConsulta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("---------------------------------------");
        System.out.println("Entrada   Saída     Horas");

        Duration totalTrabalhado = Duration.ZERO;
        DateTimeFormatter horaFmt = DateTimeFormatter.ofPattern("HH:mm");

        // Lógica dos pares (Entrada e Saída)
        for (int i = 0; i < registros.size(); i += 2)
        {
            if (i + 1 < registros.size())
            {
                RegistroPonto entrada = registros.get(i);
                RegistroPonto saida = registros.get(i + 1);

                Duration intervalo = Duration.between(entrada.getDataHora(), saida.getDataHora());
                totalTrabalhado = totalTrabalhado.plus(intervalo);

                System.out.println(
                        entrada.getDataHora().format(horaFmt) + "     " +
                                saida.getDataHora().format(horaFmt) + "     " +
                                formatarDuracao(intervalo)
                );
            }
        }

        System.out.println("---------------------------------------");
        System.out.println("Total trabalhado:   " + formatarDuracao(totalTrabalhado));
    }

    // Método auxiliar para formatar HH:mm
    private static String formatarDuracao(Duration duracao)
    {
        long horas = duracao.toHours();
        long minutos = duracao.toMinutesPart();
        return String.format("%02d:%02d", horas, minutos);
    }
}