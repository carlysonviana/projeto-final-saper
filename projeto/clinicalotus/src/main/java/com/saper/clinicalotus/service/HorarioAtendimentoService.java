package com.saper.clinicalotus.service;
import com.saper.clinicalotus.dto.HorarioAtendimentoRequestDTO;
import com.saper.clinicalotus.dto.HorarioAtendimentoResponseDTO;
import com.saper.clinicalotus.model.HorarioAtendimento;
import com.saper.clinicalotus.repository.HorarioAtendimentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.saper.clinicalotus.util.ConversorDiaDaSemana.converterParaIngles;

@Service
public class HorarioAtendimentoService {
    @Autowired
    HorarioAtendimentoRepository horarioAtendimentoRepository;

    public Object getAll(){
        return horarioAtendimentoRepository.findAll().stream().map((horarioAtendimento -> new HorarioAtendimentoResponseDTO(horarioAtendimento)));
    }

    @Transactional
    public Object save(HorarioAtendimentoRequestDTO horarioAtendimentoRequestDTO){
        HorarioAtendimento horarioAtendimento = new HorarioAtendimento(horarioAtendimentoRequestDTO);

        horarioAtendimento = horarioAtendimentoRepository.save(horarioAtendimento);

        HorarioAtendimentoResponseDTO horarioAtendimentoResponseDTO = new HorarioAtendimentoResponseDTO(horarioAtendimento);

        return horarioAtendimentoResponseDTO;
    }

    public ResponseEntity<Object> findById(Long id) {
        HorarioAtendimento horarioAtendimento = horarioAtendimentoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Horário não encontrado!"));

            return ResponseEntity.status(HttpStatus.OK).body(new HorarioAtendimentoResponseDTO(horarioAtendimento));
    }

    @Transactional
    public Object update(Long id, HorarioAtendimentoRequestDTO horarioAtendimentoRequestDTO) {
        HorarioAtendimento horarioAtendimento = horarioAtendimentoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Horário não encontrado!"));

            if(horarioAtendimentoRequestDTO.diaDaSemana != null){
                horarioAtendimento.setDiaDaSemana(converterParaIngles(horarioAtendimentoRequestDTO.diaDaSemana));
            }
            if(horarioAtendimentoRequestDTO.horarioInicio != null){
                horarioAtendimento.setHorarioFim(horarioAtendimentoRequestDTO.horarioInicio);
            }
            if(horarioAtendimentoRequestDTO.horarioFim != null){
                horarioAtendimento.setHorarioFim(horarioAtendimentoRequestDTO.horarioFim);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new HorarioAtendimentoResponseDTO(horarioAtendimentoRepository.save(horarioAtendimento)));
    }

    public ResponseEntity<Object> delete(Long id) {
        HorarioAtendimento horarioAtendimento = horarioAtendimentoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Horário não encontrado!"));

            horarioAtendimentoRepository.delete(horarioAtendimento);
            return ResponseEntity.status(HttpStatus.OK).build();
    }
}
