package com.saper.clinicalotus.model;

import com.saper.clinicalotus.dto.EstadoRequestDTO;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long Id;

    @Column(
            nullable = false,
            unique = true
    )
    private String nome;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    Set<Cidade> cidades;
    public Estado() {
    }

    public Estado(EstadoRequestDTO estadoRequestDTO) {
        this.nome = estadoRequestDTO.nome;
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

    public Set<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(Set<Cidade> cidades) {
        this.cidades = cidades;
    }
}
