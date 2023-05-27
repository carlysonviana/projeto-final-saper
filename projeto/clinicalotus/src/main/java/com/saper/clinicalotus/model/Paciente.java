package com.saper.clinicalotus.model;
import com.saper.clinicalotus.dto.PacienteRequestDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

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

    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    Set<Consulta> consultas;


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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }
}
