package com.saper.clinicalotus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PacienteRequestDTO {
    public String cpf;
    public String nome;
    public String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dataNascimento;
}
