package com.example.Sistema_De_Cartao_Ponto_SpringBoot.service;

import com.example.Sistema_De_Cartao_Ponto_SpringBoot.entity.Funcionario;
import com.example.Sistema_De_Cartao_Ponto_SpringBoot.entity.RegistroPonto;
import com.example.Sistema_De_Cartao_Ponto_SpringBoot.repository.RegistroPontoRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RelatorioService {

    private final RegistroPontoRepository registroPontoRepository;

    public RelatorioService(RegistroPontoRepository registroPontoRepository) {
        this.registroPontoRepository = registroPontoRepository;
    }

    public void gerarRelatorio(Funcionario funcionario, LocalDate data) {

        LocalDateTime inicioDia = data.atStartOfDay();
        LocalDateTime fimDia = data.atTime(LocalTime.MAX);

        List<RegistroPonto> registros = registroPontoRepository
                .findByFuncionarioAndDataHoraBetweenOrderByDataHoraAsc(funcionario, inicioDia, fimDia);

        DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fmtHora = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println();
        System.out.println("RELATÓRIO DE PONTO");
        System.out.println("Funcionário: " + funcionario.getNomeCompleto());
        System.out.println("Departamento: " + funcionario.getDepartamento().getNome());
        System.out.println("Data: " + data.format(fmtData));
        System.out.println();
        System.out.printf("%-10s %-10s %-10s%n", "Entrada", "Saída", "Horas");
        System.out.println("----------------------------");

        Duration totalTrabalhado = Duration.ZERO;

        for (int i = 0; i + 1 < registros.size(); i += 2) {
            LocalDateTime entrada = registros.get(i).getDataHora();
            LocalDateTime saida = registros.get(i + 1).getDataHora();

            Duration duracao = Duration.between(entrada, saida);
            totalTrabalhado = totalTrabalhado.plus(duracao);

            String horaEntrada = entrada.format(fmtHora);
            String horaSaida = saida.format(fmtHora);
            String horas = formatarDuracao(duracao);

            System.out.printf("%-10s %-10s %-10s%n", horaEntrada, horaSaida, horas);
        }

        System.out.println("----------------------------");
        System.out.println("Total trabalhado: " + formatarDuracao(totalTrabalhado));
        System.out.println();
    }

    private String formatarDuracao(Duration duracao) {
        long horas = duracao.toHours();
        long minutos = duracao.toMinutesPart();
        return String.format("%02d:%02d", horas, minutos);
    }
}