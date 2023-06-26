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
    public Long medico_id;
    public boolean confirmada;

    public Long prontuario_id;

    public ConsultaResponseDTO(Consulta consulta) {
        this.consulta_id = consulta.getId();
        this.dataHora = consulta.getDataHora();
        this.autorizacaoPlano = consulta.isAutorizacaoPlano();
        this.paciente_id = consulta.getPaciente().getId();
        this.medico_id = consulta.getMedico().getFuncionario().getId();
        this.confirmada = consulta.isConfirmada();
        this.prontuario_id = (consulta.getProntuario() != null) ? consulta.getProntuario().getId(): null;
    }
}
