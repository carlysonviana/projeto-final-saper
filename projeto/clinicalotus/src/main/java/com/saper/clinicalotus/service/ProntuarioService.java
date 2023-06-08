package com.saper.clinicalotus.service;


import com.saper.clinicalotus.dto.ProntuarioRequestDTO;
import com.saper.clinicalotus.dto.ProntuarioResponseDTO;
import com.saper.clinicalotus.model.Paciente;
import com.saper.clinicalotus.model.Prontuario;
import com.saper.clinicalotus.repository.PacienteRepository;
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
    @Autowired
    PacienteRepository pacienteRepository;

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
        Optional<Paciente> pacienteOptional;

        Prontuario prontuario = new Prontuario();

        if(prontuarioRequestDTO.paciente_id != null){
            pacienteOptional = pacienteRepository.findById(prontuarioRequestDTO.paciente_id);
            prontuario.setPaciente(pacienteOptional.get());
        }

        prontuario.setReceituario(prontuarioRequestDTO.receituario);
        prontuario.setDiagnostico(prontuarioRequestDTO.diagnostico);

        prontuario = prontuarioRepository.save(prontuario);


        return ResponseEntity.status(HttpStatus.CREATED).body( new ProntuarioResponseDTO(prontuario));
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

