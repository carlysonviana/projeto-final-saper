package com.saper.clinicalotus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saper.clinicalotus.model.Paciente;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PacienteResponseDTO {

    public long id;
    public String nome;

    public String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dataNascimento;

    public PacienteResponseDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
        this.dataNascimento = paciente.getDataNascimento();
    }
}
