package com.saper.clinicalotus.model;


import com.saper.clinicalotus.dto.ProntuarioRequestDTO;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prontuario_id")
    private Long id;
    private String receituario;

    private String diagnostico;

    @OneToOne(optional = true)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @OneToMany(mappedBy = "prontuario")
    Set<Consulta> consultas;

    public Prontuario(){}
    public Prontuario(ProntuarioRequestDTO prontuarioRequestDTO) {
        this.receituario = prontuarioRequestDTO.receituario;
        this.diagnostico = prontuarioRequestDTO.diagnostico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }


    public String getReceituario() {
        return receituario;
    }

    public void setReceituario(String receituario) {
        this.receituario = receituario;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }
}