package com.saper.clinicalotus.model;
import com.saper.clinicalotus.dto.HorarioAtendimentoRequestDTO;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static com.saper.clinicalotus.util.ConversorDiaDaSemana.converterParaIngles;

@Entity
public class HorarioAtendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "horario_id")
    private Long Id;
    @Column(nullable = false)
    private DayOfWeek diaDaSemana;
    @Column(nullable = false)
    private LocalTime horarioInicio;
    @Column(nullable = false)
    private LocalTime horarioFim;

    public HorarioAtendimento() {
    }
    public HorarioAtendimento(HorarioAtendimentoRequestDTO horarioAtendimentoRequestDTO) {
        this.diaDaSemana = converterParaIngles(horarioAtendimentoRequestDTO.diaDaSemana);
        this.horarioInicio = horarioAtendimentoRequestDTO.horarioInicio;
        this.horarioFim = horarioAtendimentoRequestDTO.horarioFim;
    }

    public DayOfWeek getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DayOfWeek diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
