package com.saper.clinicalotus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ConsultaRequestDTO {
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public LocalDateTime dataHora;
    public boolean autorizacaoPlano;
    public Long paciente_id;
    public Long medico_id;
}
