package com.saper.clinicalotus.dto;

import com.saper.clinicalotus.model.Consulta;
import com.saper.clinicalotus.model.Paciente;
import com.saper.clinicalotus.model.Prontuario;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ProntuarioResponseDTO {

      public Long id;

      public Long paciente_id;

      public String receituario;

      public String diagnostico;

      public List<Long> consultas;

      public ProntuarioResponseDTO(Prontuario prontuario) {
            this.id = prontuario.getId();
            this.paciente_id = prontuario.getPaciente().getId();
            this.receituario = prontuario.getReceituario();
            this.diagnostico = prontuario.getDiagnostico();
            Set<Consulta> consultas = prontuario.getConsultas();
            if(consultas != null){
                  this.consultas = prontuario.getConsultas().stream().map(consulta -> consulta.getId()).toList();
            }
            else{
                  this.consultas = Collections.emptyList();
            }
      }
}
