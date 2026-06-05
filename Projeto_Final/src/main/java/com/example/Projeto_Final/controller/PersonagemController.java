package com.example.Projeto_Final.controller;

import com.example.Projeto_Final.model.Personagem;
import com.example.Projeto_Final.repository.PersonagemRepository;
import com.example.Projeto_Final.service.MistralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class PersonagemController {

    @Autowired
    private PersonagemRepository repository;

    @Autowired
    private MistralService mistralService;

    @GetMapping("/")
    public String raiz() {
        return "redirect:/personagens";
    }

    @GetMapping("/personagens")
    public String listarTodos(Model model) {
        model.addAttribute("personagens", repository.findAll());
        return "galeria";
    }

    @GetMapping("/personagens/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("personagem", new Personagem());
        return "formulario";
    }

    @PostMapping("/personagens/salvar")
    public String salvarPersonagem(@ModelAttribute("personagem") Personagem personagem) {

        String comandoAtributos = "Gere uma lista de 4 atributos de RPG (Ex: Força, Agilidade, Inteligência, Vitalidade) com valores de 1 a 20 para o personagem chamado "
                + personagem.getNome() + " que é da classe " + personagem.getClasse() + ". Seja direto, traga apenas os atributos e valores.";

        String comandoHistoria = "Escreva uma história de fundo corta (máximo 3 linhas) para o personagem "
                + personagem.getNome() + " da classe " + personagem.getClasse() + ". Seja criativo.";

        String comandoFraqueza = "Diga uma fraqueza ou defeito marcante e curto para o personagem "
                + personagem.getNome() + " da classe " + personagem.getClasse() + ". Apenas uma frase corta.";

        personagem.setAtributos(mistralService.pedirParaIA(comandoAtributos));
        personagem.setHistoria(mistralService.pedirParaIA(comandoHistoria));
        personagem.setFraqueza(mistralService.pedirParaIA(comandoFraqueza));

        repository.save(personagem);

        return "redirect:/personagens";
    }
}