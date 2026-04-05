package com.example.Sistema_De_Cartao_Ponto_SpringBoot.repository;

import com.example.Sistema_De_Cartao_Ponto_SpringBoot.entity.Departamento;
import com.example.Sistema_De_Cartao_Ponto_SpringBoot.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    Optional<Funcionario> findByMatricula(String matricula);

    List<Funcionario> findByDepartamento(Departamento departamento);

    List<Funcionario> findByNomeCompletoContainingIgnoreCase(String nome);
}