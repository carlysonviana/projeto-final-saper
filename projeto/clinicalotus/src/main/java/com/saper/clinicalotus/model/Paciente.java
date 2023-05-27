package com.saper.clinicalotus.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saper.clinicalotus.dto.PacienteRequestDTO;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paciente_id")
    private Long Id;

    @Column(
            nullable = false,
            unique = true
    )

    private String cpf;

    @Column(nullable = false)
    private String nome;

    private String email;

//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Paciente() {
    }

    public Paciente(PacienteRequestDTO pacienteRequestDTO) {
        this.cpf = pacienteRequestDTO.cpf;
        this.nome = pacienteRequestDTO.nome;
        this.email = pacienteRequestDTO.email;
        this.dataNascimento = pacienteRequestDTO.dataNascimento;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
