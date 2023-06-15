package com.saper.clinicalotus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EstadoRequestDTO {
    @NotBlank(message = "Nome do estado não pode ser nulo ou vazio!")
    @Size(min = 3, max = 100, message = "Tamanho entre 3 e 100!")
    public String nome;
}
