package com.saper.clinicalotus.service;
import com.saper.clinicalotus.dto.HorarioAtendimentoRequestDTO;
import com.saper.clinicalotus.dto.HorarioAtendimentoResponseDTO;
import com.saper.clinicalotus.model.HorarioAtendimento;
import com.saper.clinicalotus.model.Medico;
import com.saper.clinicalotus.repository.HorarioAtendimentoRepository;
import com.saper.clinicalotus.repository.MedicoRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.saper.clinicalotus.util.ConversorDiaDaSemana.converterParaIngles;

@Service
public class HorarioAtendimentoService {
    @Autowired
    HorarioAtendimentoRepository horarioAtendimentoRepository;

    @Autowired
    MedicoRepository medicoRepository;

    public Object getAll(){
        return horarioAtendimentoRepository.findAll().stream().map((horarioAtendimento -> new HorarioAtendimentoResponseDTO(horarioAtendimento)));
    }

    @Transactional
    public Object save(HorarioAtendimentoRequestDTO horarioAtendimentoRequestDTO){
        HorarioAtendimento horarioAtendimento = new HorarioAtendimento(horarioAtendimentoRequestDTO);

        //Verifcar se medico existe
        Optional<Medico> optionalMedico = medicoRepository.findById(horarioAtendimentoRequestDTO.medico_id);

        if(optionalMedico.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico não encontrado!");
        }

        horarioAtendimento.setMedico(optionalMedico.get());
        horarioAtendimento = horarioAtendimentoRepository.save(horarioAtendimento);

        HorarioAtendimentoResponseDTO horarioAtendimentoResponseDTO = new HorarioAtendimentoResponseDTO(horarioAtendimento);

        return horarioAtendimentoResponseDTO;
    }

    public ResponseEntity<Object> findById(Long id) {
        Optional<HorarioAtendimento> horarioAtendimentoOptional = horarioAtendimentoRepository.findById(id);

        if(horarioAtendimentoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horário não encontrado");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new HorarioAtendimentoResponseDTO(horarioAtendimentoOptional.get()));
        }
    }

    @Transactional
    public Object update(Long id, HorarioAtendimentoRequestDTO horarioAtendimentoRequestDTO) {
        Optional<HorarioAtendimento> horarioAtendimentoOptional = horarioAtendimentoRepository.findById(id);

        if(horarioAtendimentoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horário não encontrado!");
        }else{
            HorarioAtendimento horarioAtendimento = horarioAtendimentoOptional.get();

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
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<HorarioAtendimento> horarioAtendimentoOptional = horarioAtendimentoRepository.findById(id);

        if(horarioAtendimentoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horário não encontrado!");
        }else{
            horarioAtendimentoRepository.delete(horarioAtendimentoOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
