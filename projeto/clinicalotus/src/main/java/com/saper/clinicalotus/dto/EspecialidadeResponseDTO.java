package com.saper.clinicalotus.dto;

import com.saper.clinicalotus.model.Especialidade;

public class EspecialidadeResponseDTO {

    public Long especialidade_id;
    public String nome;
    public String descricao;

    public EspecialidadeResponseDTO(Especialidade especialidade) {
        this.especialidade_id = especialidade.getId();
        this.nome = especialidade.getNome();
        this.descricao = especialidade.getDescricao();
    } 
}
