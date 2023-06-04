package com.saper.clinicalotus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Medico {
    
    @Id
    private Long id;
    @OneToOne
    @MapsId
    private Funcionario funcionario;

    private String crm;

    @ManyToOne(optional = true)
    @JoinColumn(name = "especialidade_id", nullable = true)
    private Especialidade especialidade;

    public Medico(){

    }
    
    public Medico(Long id, Funcionario funcionario, String crm, Especialidade especialidade) {
        this.id = id;
        this.funcionario = funcionario;
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
    
}
