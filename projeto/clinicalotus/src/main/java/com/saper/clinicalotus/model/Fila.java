package com.saper.clinicalotus.model;

import com.saper.clinicalotus.dto.FilaRequestDTO;
import jakarta.persistence.*;

@Entity
public class Fila {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fila_id")
    private Long id;

    private String tipoDeFila;

    //Especialidade especialiade;

    //Paciente paciente;

    public Fila() {
    }

    public Fila(FilaRequestDTO filaRequestDTO) {

        this.tipoDeFila = filaRequestDTO.tipoDeFila;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoDeFila() {
        return tipoDeFila;
    }

    public void setTipoDeFila(String tipoDeFila) {
        this.tipoDeFila = tipoDeFila;
    }
}
