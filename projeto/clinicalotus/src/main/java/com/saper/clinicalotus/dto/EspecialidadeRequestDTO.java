package com.saper.clinicalotus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EspecialidadeRequestDTO {

    @NotBlank(message = "Nome da especialidade não pode ser nulo ou vazio!")
    @Size(min = 3,max = 60,message = "Tamnho entre 3 e 60!")
    public String nome;

    @NotBlank(message = "Descrição da especialidade não pode ser nulo ou vazio!")
    @Size(min = 3,max = 200,message = "Tamnho entre 3 e 200!")
    public String descricao;
}
