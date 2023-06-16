package com.saper.clinicalotus.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saper.clinicalotus.model.Funcionario;

import io.micrometer.common.lang.Nullable;

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
    @Nullable
    public Long endereco_id;

    public Long categoriaFuncionario_id;

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.email = funcionario.getEmail();
        this.telefone = funcionario.getTelefone();
        this.celular = funcionario.getCelular();
        this.dataNascimento = funcionario.getDataNascimento();
        this.dataAdmissao = funcionario.getDataAdmissao();
        this.endereco_id = funcionario.getEndereco().getId();
        this.categoriaFuncionario_id = funcionario.getCategoriaFuncionario().getId();
    }
}
