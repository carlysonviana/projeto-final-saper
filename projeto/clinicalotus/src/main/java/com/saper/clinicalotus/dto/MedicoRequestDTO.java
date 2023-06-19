package com.saper.clinicalotus.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MedicoRequestDTO {

    @NotBlank(message = "Crm não pode ser vazio ou nulo")
    @Size(min = 3,max = 60,message = "Tamanho entre 3 e 60!")
    public String crm;

    @NotNull
    @Min(1)
    public Long especialidade_id;
    
}
