package com.saper.clinicalotus.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.saper.clinicalotus.dto.MedicoRequestDTO;
import com.saper.clinicalotus.dto.MedicoResponseDTO;
import com.saper.clinicalotus.model.Medico;
import com.saper.clinicalotus.repository.MedicoRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public ResponseEntity<Object> save(MedicoRequestDTO medicoRequestDTO){

        Medico medico = new Medico();
        medico.setCrm(medicoRequestDTO.crm);

        medicoRepository.save(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MedicoResponseDTO(medico));
    }

    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.findAll()
                             .stream().map((medico -> new MedicoResponseDTO(medico))));
    }
    
    public ResponseEntity<Object> findById(Long id) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);

        if(medicoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico não encontrado!");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new MedicoResponseDTO(medicoOptional.get()));
        }
    }
    @Transactional
    public Object update(Long id, MedicoRequestDTO medicoRequestDTO) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);

        if(medicoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
        }else{
            Medico medico = medicoOptional.get();

            if(medicoRequestDTO.crm != null){
                medico.setCrm(medicoRequestDTO.crm);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new MedicoResponseDTO(medicoRepository.save(medico)));
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);

        if(medicoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico não encontrado!");
        }else{
            medicoRepository.delete(medicoOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
    
}