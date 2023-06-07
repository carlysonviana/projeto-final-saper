package com.saper.clinicalotus.model;


import com.saper.clinicalotus.dto.PlanoDeSaudeRequestDTO;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class PlanoDeSaude {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "plano_id")
    private Long id;
    @Column(nullable = false)
    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "planoDeSaude", cascade = CascadeType.ALL)
    Set<Paciente> pacientes;
    
    public PlanoDeSaude() {
    }

    public PlanoDeSaude(PlanoDeSaudeRequestDTO planoDeSaudeRequestDTO){
        this.nome = planoDeSaudeRequestDTO.nome;
        this.descricao = planoDeSaudeRequestDTO.descricao;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(Set<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
}
