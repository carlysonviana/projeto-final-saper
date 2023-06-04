package com.saper.clinicalotus.model;
import com.saper.clinicalotus.dto.CidadeRequestDTO;
import jakarta.persistence.*;

@Entity
public class Cidade {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cidade_id")
    private Long Id;

    @Column(
            nullable = false,
            unique = true
    )
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
    public Cidade() {
    }

    public Cidade(CidadeRequestDTO cidadeRequestDTO) {
        this.nome = cidadeRequestDTO.nome;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
