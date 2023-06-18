package com.saper.clinicalotus.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    Set<Consulta> consultas;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    Set<HorarioAtendimento> horarioAtendimentos;

    public Medico(){

    }
    
    public Medico(Funcionario funcionario, String crm, Especialidade especialidade) {
        this.funcionario = funcionario;
        this.crm = crm;
        this.especialidade = especialidade;
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

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }

    public Set<HorarioAtendimento> getHorarioAtendimentos() {
        return horarioAtendimentos;
    }

    public void setHorarioAtendimentos(Set<HorarioAtendimento> horarioAtendimentos) {
        this.horarioAtendimentos = horarioAtendimentos;
    }
}
