package com.saper.clinicalotus.dto;

import jakarta.validation.constraints.*;

public class CidadeRequestDTO {

    @NotBlank(message = "Nome da cidade não pode ser nulo ou vazio!")
    @Size(min = 3,max = 60,message = "Tamanho entre 3 e 60!")
    public String nome;

    @NotNull
    @Min(1)
    public Long estado_id;
}
