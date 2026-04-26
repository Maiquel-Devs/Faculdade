package com.example.atividadeapi.repository;

import com.example.atividadeapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>
{

}
