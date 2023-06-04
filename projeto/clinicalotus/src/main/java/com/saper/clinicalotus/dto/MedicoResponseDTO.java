package com.saper.clinicalotus.dto;

import com.saper.clinicalotus.model.Medico;

public class MedicoResponseDTO {

    public Long funcionario_id;
    public String crm;
    public Long especialidade_id;

    public MedicoResponseDTO(Medico medico) {
        this.funcionario_id = medico.getFuncionario().getId();
        this.crm = medico.getCrm();
    }
    
}
