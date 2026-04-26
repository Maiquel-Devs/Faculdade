package com.example.atividadeapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Filme
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo; // Ex: Star Wars

    private int estrelas; // Ex: 5

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}