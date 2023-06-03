package com.saper.clinicalotus.dto;

import com.saper.clinicalotus.model.PlanoDeSaude;

public class PlanoDeSaudeResponseDTO {

    public Long id;
    public String nome;

    public String descricao;

    public PlanoDeSaudeResponseDTO(PlanoDeSaude planoDeSaude) {
        this.id = planoDeSaude.getId();
        this.nome = planoDeSaude.getNome();
        this.descricao = planoDeSaude.getDescricao();
    }
}
