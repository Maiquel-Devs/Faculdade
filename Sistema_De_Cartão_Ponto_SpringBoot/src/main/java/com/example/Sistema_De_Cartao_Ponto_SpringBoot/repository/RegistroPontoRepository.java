package com.example.Sistema_De_Cartao_Ponto_SpringBoot.repository;

import com.example.Sistema_De_Cartao_Ponto_SpringBoot.entity.Funcionario;
import com.example.Sistema_De_Cartao_Ponto_SpringBoot.entity.RegistroPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Integer> {

    List<RegistroPonto> findByFuncionarioAndDataHoraBetweenOrderByDataHoraAsc(
            Funcionario funcionario,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    List<RegistroPonto> findByFuncionarioOrderByDataHoraAsc(Funcionario funcionario);

    long countByFuncionarioAndDataHoraBetween(
            Funcionario funcionario,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}