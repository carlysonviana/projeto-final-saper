package com.saper.clinicalotus.dto;

import com.saper.clinicalotus.model.Fila;

public class FilaResponseDTO {

    public Long id;
     //public Long paciente_id;
    //public Long especialidade_id;
    public String tipoDeFila;

    public FilaResponseDTO(Fila fila) {
        this.id = fila.getId();
        this.tipoDeFila = fila.getTipoDeFila();
    }

}
