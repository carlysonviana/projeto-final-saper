package com.saper.clinicalotus.dto;
import com.saper.clinicalotus.model.Cidade;

public class CidadeResponseDTO {
    public Long id;
    public String nome;
    public Long estado_id;

    public CidadeResponseDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.estado_id = cidade.getEstado().getId();
    }
}
