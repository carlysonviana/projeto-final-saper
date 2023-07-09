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

    @ManyToOne(optional = true)
    @JoinColumn(name = "endereco_id", nullable = true)
    private Endereco endereco;

    @ManyToOne(optional = true)
    @JoinColumn(name = "plano_id", nullable = true)
    private PlanoDeSaude planoDeSaude;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private Prontuario prontuario;
    public Paciente() {
    }

    public Paciente(String cpf, String nome, String email, LocalDate dataNascimento, Endereco endereco, PlanoDeSaude planoDeSaude) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.planoDeSaude = planoDeSaude;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public PlanoDeSaude getPlanoDeSaude() {
        return planoDeSaude;
    }

    public void setPlanoDeSaude(PlanoDeSaude planoDeSaude) {
        this.planoDeSaude = planoDeSaude;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }
}
