package com.example.Sistema_De_Cartao_Ponto_SpringBoot.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "matricula", nullable = false, unique = true, length = 20)
    private String matricula;

    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto;

    @ManyToOne
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<RegistroPonto> registrosPonto;

    public Funcionario() {}

    public Funcionario(String matricula, String nomeCompleto, Departamento departamento) {
        this.matricula = matricula;
        this.nomeCompleto = nomeCompleto;
        this.departamento = departamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<RegistroPonto> getRegistrosPonto() {
        return registrosPonto;
    }

    public void setRegistrosPonto(List<RegistroPonto> registrosPonto) {
        this.registrosPonto = registrosPonto;
    }

    @Override
    public String toString() {
        return "Funcionario{id=" + id + ", matricula='" + matricula + "', nomeCompleto='" + nomeCompleto + "'}";
    }
}