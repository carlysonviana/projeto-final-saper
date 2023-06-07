package com.saper.clinicalotus.dto;

import com.saper.clinicalotus.model.Prontuario;

public class ProntuarioResponseDTO {

      public Long id;

    //public Long medico_id;

    //  public Long paciente_id;

      public String receituario;

      public String diagnostico;

      public ProntuarioResponseDTO(Prontuario prontuario) {
            this.id = prontuario.getId();
            this.receituario = prontuario.getReceituario();
            this.diagnostico = prontuario.getDiagnostico();
      }
}
