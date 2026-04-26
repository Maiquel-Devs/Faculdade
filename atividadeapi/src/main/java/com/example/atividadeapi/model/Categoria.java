package com.example.atividadeapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Categoria
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome; // Ex: Ficção Científica, Drama, Terror
}
