package com.example.FinTrack.controller;

import com.example.FinTrack.model.Ativo;
import com.example.FinTrack.model.Despesa;
import com.example.FinTrack.model.Usuario;
import com.example.FinTrack.repository.AtivoRepository;
import com.example.FinTrack.repository.DespesaRepository;
import com.example.FinTrack.repository.UsuarioRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/")
public class FinanceiroController implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final AtivoRepository ativoRepository;
    private final DespesaRepository despesaRepository;

    public FinanceiroController(UsuarioRepository usuarioRepository, AtivoRepository ativoRepository, DespesaRepository despesaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.ativoRepository = ativoRepository;
        this.despesaRepository = despesaRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (usuarioRepository.count() == 0) {
            Usuario novo = new Usuario();
            novo.setNome("Usuário Padrão");
            usuarioRepository.save(novo);
        }
    }

    @GetMapping
    public String index(Model model) {
        return carregarDashboard(model, new Ativo(), new Despesa());
    }

    // Rota que joga o Ativo selecionado de volta para o formulário
    @GetMapping("/ativos/editar/{id}")
    public String editarAtivo(@PathVariable("id") Long id, Model model) {
        Ativo ativo = ativoRepository.findById(id).orElseThrow();
        return carregarDashboard(model, ativo, new Despesa());
    }

    // Rota que joga a Despesa selecionada de volta para o formulário
    @GetMapping("/despesas/editar/{id}")
    public String editarDespesa(@PathVariable("id") Long id, Model model) {
        Despesa despesa = despesaRepository.findById(id).orElseThrow();
        return carregarDashboard(model, new Ativo(), despesa);
    }

    @PostMapping("/ativos/salvar")
    public String salvarAtivo(@ModelAttribute("novoAtivo") Ativo ativo) {
        Usuario usuario = usuarioRepository.findAll().stream().findFirst().orElseThrow();
        ativo.setUsuario(usuario);
        ativoRepository.save(ativo); // O .save() do JPA atualiza automaticamente se o objeto já contiver um ID
        return "redirect:/";
    }

    @PostMapping("/despesas/salvar")
    public String salvarDespesa(@ModelAttribute("novaDespesa") Despesa despesa) {
        Usuario usuario = usuarioRepository.findAll().stream().findFirst().orElseThrow();
        despesa.setUsuario(usuario);
        despesaRepository.save(despesa); // O .save() do JPA atualiza automaticamente se o objeto já contiver um ID
        return "redirect:/";
    }

    @GetMapping("/ativos/deletar/{id}")
    public String deletarAtivo(@PathVariable("id") Long id) {
        ativoRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/despesas/deletar/{id}")
    public String deletarDespesa(@PathVariable("id") Long id) {
        despesaRepository.deleteById(id);
        return "redirect:/";
    }

    // Método auxiliar para evitar duplicação de código na montagem da tela
    private String carregarDashboard(Model model, Ativo ativoForm, Despesa despesaForm) {
        List<Ativo> ativos = ativoRepository.findAll();
        List<Despesa> despesas = despesaRepository.findAll();

        BigDecimal totalAtivos = ativos.stream().map(Ativo::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalDespesas = despesas.stream().map(Despesa::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("ativos", ativos);
        model.addAttribute("despesas", despesas);
        model.addAttribute("totalAtivos", totalAtivos);
        model.addAttribute("totalDespesas", totalDespesas);

        model.addAttribute("novoAtivo", ativoForm);
        model.addAttribute("novaDespesa", despesaForm);

        return "index";
    }
}