package com.example.FinTrack.repository;

import com.example.FinTrack.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {}