package com.saper.clinicalotus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saper.clinicalotus.model.Consulta;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConsultaResponseDTO {
    public Long consulta_id;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public LocalDateTime dataHora;
    public boolean autorizacaoPlano;
    public Long paciente_id;
    public boolean confirmada;

    public ConsultaResponseDTO(Consulta consulta) {
        this.consulta_id = consulta.getId();
        this.dataHora = consulta.getDataHora();
        this.autorizacaoPlano = consulta.isAutorizacaoPlano();
        this.paciente_id = consulta.getPaciente().getId();
        this.confirmada = consulta.isConfirmada();
    }
}
