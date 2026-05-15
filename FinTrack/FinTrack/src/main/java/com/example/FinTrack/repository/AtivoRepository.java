package com.example.FinTrack.repository;

import com.example.FinTrack.model.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtivoRepository extends JpaRepository<Ativo, Long> {}