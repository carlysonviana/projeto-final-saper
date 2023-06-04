package com.saper.clinicalotus.model;

import java.time.LocalDate;

import com.saper.clinicalotus.dto.FuncionarioRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Long Id;

    @Column(
            nullable = false,
            unique = true
    )

    private String cpf;

    @Column(nullable = false)
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String telefone;
    private String celular;
    private LocalDate dataAdmissao;

    @ManyToOne
    @JoinColumn(name = "categoriaFuncionario_id")
    private CategoriaFuncionario categoriaFuncionario;

    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Medico medico;

    public Funcionario(){

    }

    public Funcionario(FuncionarioRequestDTO funcionarioRequestDTO) {
        this.nome = funcionarioRequestDTO.nome;
        this.cpf = funcionarioRequestDTO.cpf;
        this.email = funcionarioRequestDTO.email;
        this.dataNascimento = funcionarioRequestDTO.dataNascimento;
        this.telefone = funcionarioRequestDTO.telefone;
        this.celular = funcionarioRequestDTO.celular;
        this.dataAdmissao = funcionarioRequestDTO.dataAdmissao;
        this.categoriaFuncionario = funcionarioRequestDTO.categoriaFuncionario;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public CategoriaFuncionario getCategoriaFuncionario() {
        return categoriaFuncionario;
    }

    public void setCategoriaFuncionario(CategoriaFuncionario categoriaFuncionario) {
        this.categoriaFuncionario = categoriaFuncionario;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((dataAdmissao == null) ? 0 : dataAdmissao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Funcionario other = (Funcionario) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (dataAdmissao == null) {
            if (other.dataAdmissao != null)
                return false;
        } else if (!dataAdmissao.equals(other.dataAdmissao))
            return false;
        return true;
    }
}
