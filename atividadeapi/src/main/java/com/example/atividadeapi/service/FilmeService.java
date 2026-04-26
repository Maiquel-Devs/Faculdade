package com.example.atividadeapi.service;

import com.example.atividadeapi.model.Filme;
import com.example.atividadeapi.model.Categoria;
import com.example.atividadeapi.repository.FilmeRepository;
import com.example.atividadeapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FilmeService
{
    @Autowired private FilmeRepository filmeRepo;
    @Autowired private CategoriaRepository catRepo;

    public List<Filme> listarTodos() { return filmeRepo.findAll(); }

    public Filme salvar(Filme filme) { return filmeRepo.save(filme); }

    public List<Categoria> listarCategorias() { return catRepo.findAll(); }

    public Categoria salvarCategoria(Categoria cat) { return catRepo.save(cat); }
}