package com.saper.clinicalotus.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saper.clinicalotus.model.Endereco;

import io.micrometer.common.lang.Nullable;

public class FuncionarioRequestDTO {

    public String nome;
    public String cpf;
    public String email;
    public String telefone;
    public String celular;

    public String login;
    public String senha;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dataNascimento;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dataAdmissao;
    @Nullable
    public Endereco endereco;

    public Long categoriaFuncionario_id;
    
}
