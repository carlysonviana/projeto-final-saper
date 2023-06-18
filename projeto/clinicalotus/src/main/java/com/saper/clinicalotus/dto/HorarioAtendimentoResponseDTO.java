package com.saper.clinicalotus.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saper.clinicalotus.model.HorarioAtendimento;
import java.time.LocalTime;

import static com.saper.clinicalotus.util.ConversorDiaDaSemana.converterParaPortugues;

public class HorarioAtendimentoResponseDTO {
    public long horario_id;
    public String diaDaSemana;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    public LocalTime horarioInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    public LocalTime horarioFim;
    public Long medico_id;

    public HorarioAtendimentoResponseDTO(HorarioAtendimento horarioAtendimento) {
        this.horario_id = horarioAtendimento.getId();
        this.diaDaSemana = converterParaPortugues(horarioAtendimento.getDiaDaSemana());
        this.horarioInicio = horarioAtendimento.getHorarioInicio();
        this.horarioFim = horarioAtendimento.getHorarioFim();
        this.medico_id = horarioAtendimento.getMedico().getFuncionario().getId();
    }
}
