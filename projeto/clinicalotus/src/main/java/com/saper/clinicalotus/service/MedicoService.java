package com.saper.clinicalotus.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.saper.clinicalotus.dto.MedicoRequestDTO;
import com.saper.clinicalotus.dto.MedicoResponseDTO;
import com.saper.clinicalotus.model.Especialidade;
import com.saper.clinicalotus.model.Medico;
import com.saper.clinicalotus.repository.EspecialidadeRepository;
import com.saper.clinicalotus.repository.MedicoRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    EspecialidadeRepository especialidadeRepository;

    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.findAll()
                             .stream().map((medico -> new MedicoResponseDTO(medico))));
    }
    
    public ResponseEntity<Object> findById(Long id) {
        Medico medico = medicoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Medico não encontrado!"));

            return ResponseEntity.status(HttpStatus.OK).body(new MedicoResponseDTO(medico));
    }
    @Transactional
    public Object update(Long id, MedicoRequestDTO medicoRequestDTO) {
        Medico medico = medicoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Medico não encontrado!"));
        Especialidade especialidade = especialidadeRepository.findById(medicoRequestDTO.especialidade_id).orElseThrow(()-> new NoSuchElementException("Especialidade não encontrada!"));

            if(medicoRequestDTO.crm != null){
                medico.setCrm(medicoRequestDTO.crm);
            }
            if(medicoRequestDTO.especialidade_id != null){
                medico.setEspecialidade(especialidade);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new MedicoResponseDTO(medicoRepository.save(medico)));
    }

    public ResponseEntity<Object> delete(Long id) {
        Medico medico = medicoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Medico não encontrado!"));

            medicoRepository.delete(medico);
            return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
