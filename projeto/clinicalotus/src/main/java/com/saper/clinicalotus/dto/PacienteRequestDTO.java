package com.saper.clinicalotus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PacienteRequestDTO {

    @NotBlank(message = "cpf do funcionario não pode está em branco ou vazio.")
    @Size(min = 11,max = 14)
    public String cpf;

    @NotBlank(message = "Nome do paciente não pode ser nulo ou vazio.")
    public String nome;

    @Email
    public String email;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dataNascimento;

    @Nullable
    public Long endereco_id;

    @Nullable
    public Long plano_id;
}
