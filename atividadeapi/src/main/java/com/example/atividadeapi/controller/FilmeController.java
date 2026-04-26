package com.example.atividadeapi.controller;

import com.example.atividadeapi.model.Filme;
import com.example.atividadeapi.model.Categoria;
import com.example.atividadeapi.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FilmeController
{

    @Autowired
    private FilmeService service;

    @GetMapping("/categorias")
    public List<Categoria> getCategorias() { return service.listarCategorias(); }

    @PostMapping("/categorias")
    public Categoria postCategoria(@RequestBody Categoria cat) { return service.salvarCategoria(cat); }

    @GetMapping("/filmes")
    public List<Filme> getFilmes() { return service.listarTodos(); }

    @PostMapping("/filmes")
    public Filme postFilme(@RequestBody Filme filme) { return service.salvar(filme); }
}