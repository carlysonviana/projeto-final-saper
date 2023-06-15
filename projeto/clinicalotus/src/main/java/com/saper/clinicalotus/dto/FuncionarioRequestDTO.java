package com.saper.clinicalotus.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saper.clinicalotus.model.Endereco;

import io.micrometer.common.lang.Nullable;

public class FuncionarioRequestDTO {

    @NotBlank(message = "Nome do funcionario não pode está em nulo ou vazio!")
    @Size(min = 3, max = 60, message = "Tamanho entre 3 e 60!")
    public String nome;

    @NotBlank(message = "cpf do funcionario não pode está em nulo ou vazio!")
    @Size(min = 11,max = 14, message = "Tamanho entre 11 e 14!")
    public String cpf;

    @Email(message ="Email precisa está no formato correto!" )
    public String email;

    @Null
    public String telefone;
    @NotBlank(message = "celular do funcionario não pode está em nulo ou vazio!")
    public String celular;

    @NotBlank(message = "login do funcionario não pode está em nulo ou vazio!")
    @Size(min = 3, max = 60, message = "Tamanho entre 3 e 60!")
    public String login;
    @NotBlank(message = "senha do funcionario não pode está em nulo ou vazio!")
    @Size(min = 3, max = 10, message = "Tamanho entre 3 e 10!")
    public String senha;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dataNascimento;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dataAdmissao;

    @Nullable
    public Endereco endereco;

    @NotNull
    @Min(1)
    public Long categoriaFuncionario_id;
    
}
