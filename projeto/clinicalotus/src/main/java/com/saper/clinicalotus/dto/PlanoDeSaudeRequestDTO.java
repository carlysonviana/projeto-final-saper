package com.saper.clinicalotus.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlanoDeSaudeRequestDTO {

    @NotBlank(message = "Nome do plano de saúde não pode ser nulo ou vazio!")
    @Size(min = 3, max = 60, message = "Tamanho entre 3 e 60!")
    public String nome;

    @NotBlank(message = "Descrição do plano de saúde não pode ser nulo ou vazio")
    @Size(min = 3, max = 200, message = "Tamanho entre 3 e 200!")
    public String descricao;


}
