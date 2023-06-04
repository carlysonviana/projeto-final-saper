package com.saper.clinicalotus.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saper.clinicalotus.model.CategoriaFuncionario;

public class FuncionarioRequestDTO {

    public String nome;
    public String cpf;
    public String email;
    public String telefone;
    public String celular;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dataNascimento;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dataAdmissao;

    public CategoriaFuncionario categoriaFuncionario;

    
}
