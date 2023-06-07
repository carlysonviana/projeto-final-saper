package com.saper.clinicalotus.dto;
import com.saper.clinicalotus.model.Paciente;
import com.saper.clinicalotus.model.PlanoDeSaude;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PlanoDeSaudeResponseDTO {

    public Long id;
    public String nome;

    public String descricao;
    public List<Long> pacientes;

    public PlanoDeSaudeResponseDTO(PlanoDeSaude planoDeSaude) {
        this.id = planoDeSaude.getId();
        this.nome = planoDeSaude.getNome();
        this.descricao = planoDeSaude.getDescricao();

        Set<Paciente> pacientes = planoDeSaude.getPacientes();
        if(pacientes != null){
            this.pacientes = planoDeSaude.getPacientes().stream().map(paciente -> paciente.getId()).toList();
        }
        else{
            this.pacientes = Collections.emptyList();
        }

    }
}
