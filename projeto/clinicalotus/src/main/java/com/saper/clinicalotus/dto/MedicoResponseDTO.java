package com.saper.clinicalotus.dto;

import com.saper.clinicalotus.model.Medico;

public class MedicoResponseDTO {

    public Long id;
    public Long funcionario_id;
    public String crm;
    public Long especialidade_id;

    public MedicoResponseDTO(Medico medico) {
        this.funcionario_id = medico.getFuncionario().getId();
        this.crm = medico.getCrm();
        //this.especialidade_id = medico.getEspecialidade().getId();
    }
    
}
