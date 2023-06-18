package com.saper.clinicalotus.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class HorarioAtendimentoRequestDTO {
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public String diaDaSemana;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    public LocalTime horarioInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    public LocalTime horarioFim;
    public Long medico_id;
}
