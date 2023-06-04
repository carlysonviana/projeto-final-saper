package com.saper.clinicalotus.service;


import com.saper.clinicalotus.dto.ProntuarioRequestDTO;
import com.saper.clinicalotus.dto.ProntuarioResponseDTO;
import com.saper.clinicalotus.model.Prontuario;
import com.saper.clinicalotus.repository.ProntuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProntuarioService {

    @Autowired
    ProntuarioRepository prontuarioRepository;

    public Object findById(Long id) {
        Optional<Prontuario> prontuarioOptional = prontuarioRepository.findById(id);

        if(prontuarioOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prontuario não  encontrado!");
        else{
            return ResponseEntity.status(HttpStatus.OK).body( new ProntuarioResponseDTO(prontuarioOptional.get()));
        }
    }


    public Object getAll() {
        return prontuarioRepository.findAll().stream().map(prontuario -> new ProntuarioResponseDTO(prontuario));
    }

    @Transactional
    public Object save(ProntuarioRequestDTO prontuarioRequestDTO) {
        Prontuario prontuario = new Prontuario(prontuarioRequestDTO);

        prontuario = prontuarioRepository.save(prontuario);

        ProntuarioResponseDTO prontuarioResponseDTO = new ProntuarioResponseDTO(prontuario);

        return prontuarioResponseDTO;
    }
    @Transactional
    public Object update(Long id, ProntuarioRequestDTO prontuarioRequestDTO) {
        Optional<Prontuario> prontuarioOptional = prontuarioRepository.findById(id);

        if(prontuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prontuario não encontrado!");
        }else{
            Prontuario prontuario = prontuarioOptional.get();

            if(prontuarioRequestDTO.diagnostico != null){
                prontuario.setDiagnostico(prontuarioRequestDTO.diagnostico);
            }
            if(prontuarioRequestDTO.receituario != null){
                prontuario.setReceituario(prontuarioRequestDTO.receituario);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ProntuarioResponseDTO(prontuarioRepository.save(prontuario)));
        }
    }

    public Object delete(Long id) {
        Optional<Prontuario> prontuarioOptional = prontuarioRepository.findById(id);

        if(prontuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prontuario não encontrado!");
        }else{
            prontuarioRepository.delete(prontuarioOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}

