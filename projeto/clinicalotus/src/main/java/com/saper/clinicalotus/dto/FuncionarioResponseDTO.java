package com.saper.clinicalotus.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saper.clinicalotus.model.Funcionario;

public class FuncionarioResponseDTO {

    public Long id;
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

    public Long categoriaFuncionario;

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.email = funcionario.getEmail();
        this.telefone = funcionario.getTelefone();
        this.celular = funcionario.getCelular();
        this.dataNascimento = funcionario.getDataNascimento();
        this.dataAdmissao = funcionario.getDataAdmissao();
        this.categoriaFuncionario = funcionario.getCategoriaFuncionario().getId();
    }
}
