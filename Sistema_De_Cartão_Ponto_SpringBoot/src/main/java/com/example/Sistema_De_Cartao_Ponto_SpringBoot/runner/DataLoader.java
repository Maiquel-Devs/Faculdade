package com.example.Sistema_De_Cartao_Ponto_SpringBoot.runner;

import com.example.Sistema_De_Cartao_Ponto_SpringBoot.entity.Departamento;
import com.example.Sistema_De_Cartao_Ponto_SpringBoot.entity.Funcionario;
import com.example.Sistema_De_Cartao_Ponto_SpringBoot.entity.RegistroPonto;
import com.example.Sistema_De_Cartao_Ponto_SpringBoot.repository.DepartamentoRepository;
import com.example.Sistema_De_Cartao_Ponto_SpringBoot.repository.FuncionarioRepository;
import com.example.Sistema_De_Cartao_Ponto_SpringBoot.repository.RegistroPontoRepository;
import com.example.Sistema_De_Cartao_Ponto_SpringBoot.service.RelatorioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final DepartamentoRepository departamentoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final RegistroPontoRepository registroPontoRepository;
    private final RelatorioService relatorioService;

    public DataLoader(DepartamentoRepository departamentoRepository,
                      FuncionarioRepository funcionarioRepository,
                      RegistroPontoRepository registroPontoRepository,
                      RelatorioService relatorioService) {
        this.departamentoRepository = departamentoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.registroPontoRepository = registroPontoRepository;
        this.relatorioService = relatorioService;
    }

    @Override
    public void run(String... args) throws Exception {

        // -------------------------
        // Inserindo Departamento
        // -------------------------
        Departamento gestao = new Departamento("Gestão de Pessoas");
        departamentoRepository.save(gestao);

        // -------------------------
        // Inserindo Funcionários
        // -------------------------
        Funcionario james = new Funcionario("12345", "James Gosling", gestao);
        funcionarioRepository.save(james);

        Funcionario linus = new Funcionario("22222", "Linus Torvalds", gestao);
        funcionarioRepository.save(linus);

        Funcionario bill = new Funcionario("33333", "Bill Gates", gestao);
        funcionarioRepository.save(bill);

        // -------------------------
        // Registros de Ponto - James Gosling
        // -------------------------
        registroPontoRepository.save(new RegistroPonto(james, LocalDateTime.of(2026, 3, 10, 8, 2)));
        registroPontoRepository.save(new RegistroPonto(james, LocalDateTime.of(2026, 3, 10, 12, 1)));
        registroPontoRepository.save(new RegistroPonto(james, LocalDateTime.of(2026, 3, 10, 13, 5)));
        registroPontoRepository.save(new RegistroPonto(james, LocalDateTime.of(2026, 3, 10, 17, 58)));

        // -------------------------
        // Registros de Ponto - Linus Torvalds
        // -------------------------
        registroPontoRepository.save(new RegistroPonto(linus, LocalDateTime.of(2026, 3, 10, 9, 0)));
        registroPontoRepository.save(new RegistroPonto(linus, LocalDateTime.of(2026, 3, 10, 12, 30)));
        registroPontoRepository.save(new RegistroPonto(linus, LocalDateTime.of(2026, 3, 10, 14, 0)));
        registroPontoRepository.save(new RegistroPonto(linus, LocalDateTime.of(2026, 3, 10, 18, 30)));

        // -------------------------
        // Registros de Ponto - Bill Gates
        // -------------------------
        registroPontoRepository.save(new RegistroPonto(bill, LocalDateTime.of(2026, 3, 10, 8, 0)));
        registroPontoRepository.save(new RegistroPonto(bill, LocalDateTime.of(2026, 3, 10, 12, 0)));
        registroPontoRepository.save(new RegistroPonto(bill, LocalDateTime.of(2026, 3, 10, 13, 30)));
        registroPontoRepository.save(new RegistroPonto(bill, LocalDateTime.of(2026, 3, 10, 17, 30)));

        LocalDate dataPonto = LocalDate.of(2026, 3, 10);

        relatorioService.gerarRelatorio(james, dataPonto);
        relatorioService.gerarRelatorio(linus, dataPonto);
        relatorioService.gerarRelatorio(bill, dataPonto);
    }
}