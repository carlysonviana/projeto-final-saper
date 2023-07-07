package com.saper.clinicalotus.model;

import com.saper.clinicalotus.dto.ConsultaRequestDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consulta_id")
    private Long id;

    @Column(
            nullable = false,
            unique = true
    )
    private LocalDateTime dataHora;
    private boolean confirmada;
    private boolean autorizacaoPlano;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "prontuario_id")
    private Prontuario prontuario;

    public Consulta(){
    }
    public Consulta(Long id, LocalDateTime dataHora, boolean autorizacaoPlano, Paciente paciente, Medico medico) {
        this.id = id;
        this.dataHora = dataHora;
        this.confirmada = false;
        this.autorizacaoPlano = autorizacaoPlano;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    public boolean isAutorizacaoPlano() {
        return autorizacaoPlano;
    }

    public void setAutorizacaoPlano(boolean autorizacaoPlano) {
        this.autorizacaoPlano = autorizacaoPlano;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }
}
