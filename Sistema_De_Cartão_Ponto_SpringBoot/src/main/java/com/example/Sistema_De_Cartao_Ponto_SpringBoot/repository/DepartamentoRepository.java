package com.example.Sistema_De_Cartao_Ponto_SpringBoot.repository;

import com.example.Sistema_De_Cartao_Ponto_SpringBoot.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

    Optional<Departamento> findByNome(String nome);

    boolean existsByNome(String nome);
}
