package com.saper.clinicalotus.service;


import com.saper.clinicalotus.dto.PlanoDeSaudeRequestDTO;
import com.saper.clinicalotus.dto.PlanoDeSaudeResponseDTO;
import com.saper.clinicalotus.model.PlanoDeSaude;
import com.saper.clinicalotus.repository.PlanoDeSaudeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanoDeSaudeService {
    @Autowired
    PlanoDeSaudeRepository planoDeSaudeRepository;

    public ResponseEntity<Object> findById(Long id) {
        Optional<PlanoDeSaude> planoDeSaudeOptional = planoDeSaudeRepository.findById(id);

        if(planoDeSaudeOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano de Saúde não  encontrado!");
        else{
            return ResponseEntity.status(HttpStatus.OK).body( new PlanoDeSaudeResponseDTO(planoDeSaudeOptional.get()));
        }

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
        Optional<PlanoDeSaude> planoDeSaudeOptional = planoDeSaudeRepository.findById(id);

        if(planoDeSaudeOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano de Saúde não encontrado!");
        }else{
            PlanoDeSaude planoDeSaude = planoDeSaudeOptional.get();

            if(planoDeSaudeRequestDTO.nome != null){
                planoDeSaude.setNome(planoDeSaudeRequestDTO.nome);
            }
            if(planoDeSaudeRequestDTO.descricao != null){
                planoDeSaude.setDescricao(planoDeSaudeRequestDTO.descricao);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new PlanoDeSaudeResponseDTO(planoDeSaudeRepository.save(planoDeSaude)));
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<PlanoDeSaude> planoDeSaudeOptional = planoDeSaudeRepository.findById(id);

        if(planoDeSaudeOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano de Saúde não encontrado!");
        }else{
            planoDeSaudeRepository.delete(planoDeSaudeOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
