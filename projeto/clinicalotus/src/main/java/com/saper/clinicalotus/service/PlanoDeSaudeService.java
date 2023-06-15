package com.saper.clinicalotus.service;


import com.saper.clinicalotus.dto.PlanoDeSaudeRequestDTO;
import com.saper.clinicalotus.dto.PlanoDeSaudeResponseDTO;
import com.saper.clinicalotus.model.Paciente;
import com.saper.clinicalotus.model.PlanoDeSaude;
import com.saper.clinicalotus.repository.PlanoDeSaudeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class PlanoDeSaudeService {
    @Autowired
    PlanoDeSaudeRepository planoDeSaudeRepository;

    public ResponseEntity<Object> findById(Long id) {
        PlanoDeSaude planoDeSaude = planoDeSaudeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Plano não encontrado!"));

            return ResponseEntity.status(HttpStatus.OK).body( new PlanoDeSaudeResponseDTO(planoDeSaude));
    }

    public Object getAll() {
        return planoDeSaudeRepository.findAll().stream().map(planoDeSaude -> new PlanoDeSaudeResponseDTO(planoDeSaude));
    }

    @Transactional
    public Object save(PlanoDeSaudeRequestDTO planoDeSaudeRequestDTO){
        PlanoDeSaude planoDeSaude = new PlanoDeSaude(planoDeSaudeRequestDTO);

        planoDeSaude = planoDeSaudeRepository.save(planoDeSaude);

        PlanoDeSaudeResponseDTO planoDeSaudeResponseDTO = new PlanoDeSaudeResponseDTO(planoDeSaude);

        return planoDeSaudeResponseDTO;
    }

    @Transactional
    public Object update(Long id, PlanoDeSaudeRequestDTO planoDeSaudeRequestDTO) {
        PlanoDeSaude planoDeSaude = planoDeSaudeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Plano não encontrado!"));

            if(planoDeSaudeRequestDTO.nome != null){
                planoDeSaude.setNome(planoDeSaudeRequestDTO.nome);
            }
            if(planoDeSaudeRequestDTO.descricao != null){
                planoDeSaude.setDescricao(planoDeSaudeRequestDTO.descricao);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new PlanoDeSaudeResponseDTO(planoDeSaudeRepository.save(planoDeSaude)));

    }

    public ResponseEntity<Object> delete(Long id) {

        PlanoDeSaude planoDeSaude = planoDeSaudeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Plano não encontrado!"));

            Set<Paciente> pacientes = planoDeSaude.getPacientes();
            for(Paciente paciente: pacientes){
                paciente.setPlanoDeSaude(null);
            }
            planoDeSaudeRepository.delete(planoDeSaude);
            return ResponseEntity.status(HttpStatus.OK).build();

    }
}
