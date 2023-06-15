package com.saper.clinicalotus.dto;

import com.saper.clinicalotus.model.Cidade;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EnderecoRequestDTO {

    @NotBlank(message = "Logradouro não pode ser nulo ou vazio!")
    @Size(min = 3, max = 200, message = "Tamanho entre 3 e 200!")
    public String logradouro;
    @NotNull
    @Min(1)
    public Long numero;
    @NotBlank(message = "Logradouro não pode ser nulo ou vazio!")
    @Size(min = 3, max = 200, message = "Tamanho entre 3 e 200!")
    public String bairro;
    @NotBlank(message = "Logradouro não pode ser nulo ou vazio!")
    @Size(min = 3, max = 200, message = "Tamanho entre 3 e 200!")
    public String complemento;
    @NotBlank(message = "Logradouro não pode ser nulo ou vazio!")
    @Size(min = 8, max = 9, message = "Tamanho entre 8 e 9!")
    public String cep;

    public Cidade cidade;
}
