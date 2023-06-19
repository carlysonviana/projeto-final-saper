package com.saper.clinicalotus.dto;

import com.saper.clinicalotus.model.Consulta;
import com.saper.clinicalotus.model.HorarioAtendimento;
import com.saper.clinicalotus.model.Medico;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MedicoResponseDTO {

    public Long funcionario_id;
    public String crm;
    public Long especialidade_id;

    public List<Long> consultas;

    public List<Long> horariosAtendimento;
    public MedicoResponseDTO(Medico medico) {
        this.funcionario_id = medico.getFuncionario().getId();
        this.crm = (medico.getCrm() != null) ? medico.getCrm() : null;
        this.especialidade_id = (medico.getEspecialidade() != null) ? medico.getEspecialidade().getId(): null;

        Set<Consulta> consultas = medico.getConsultas();
        if(consultas != null){
            this.consultas = medico.getConsultas().stream().map(consulta -> consulta.getId()).toList();
        }
        else{
            this.consultas = Collections.emptyList();
        }

        Set<HorarioAtendimento> horariosAtendimento = medico.getHorarioAtendimentos();
        if(horariosAtendimento != null){
            this.horariosAtendimento = medico.getHorarioAtendimentos().stream().map(horarioAtendimento -> horarioAtendimento.getId()).toList();
        }
        else{
            this.horariosAtendimento = Collections.emptyList();
        }
    }
}
