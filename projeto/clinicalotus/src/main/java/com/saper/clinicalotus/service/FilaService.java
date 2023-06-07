package com.saper.clinicalotus.service;


import com.saper.clinicalotus.dto.FilaRequestDTO;
import com.saper.clinicalotus.dto.FilaResponseDTO;
import com.saper.clinicalotus.model.Fila;
import com.saper.clinicalotus.repository.FilaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilaService {

    @Autowired
    FilaRepository filaRepository;

    public ResponseEntity<Object> findById(Long id) {
        Optional<Fila> filaOptional = filaRepository.findById(id);

        if(filaOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fila não  encontrada!");
        else{
            return ResponseEntity.status(HttpStatus.OK).body( new FilaResponseDTO(filaOptional.get()));
        }

    }

    public Object getAll() {
        return filaRepository.findAll().stream().map(fila -> new FilaResponseDTO(fila));
    }

    @Transactional
    public Object save(FilaRequestDTO filaRequestDTO){
        Fila fila = new Fila(filaRequestDTO);

        fila = filaRepository.save(fila);

        FilaResponseDTO filaResponseDTO = new FilaResponseDTO(fila);

        return filaResponseDTO;
    }

    @Transactional
    public Object update(Long id, FilaRequestDTO filaRequestDTO) {
        Optional<Fila> filaOptional = filaRepository.findById(id);

        if(filaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fila não encontrada!");
        }else{
            Fila fila = filaOptional.get();

            if(filaRequestDTO.tipoDeFila != null){
                fila.setTipoDeFila(filaRequestDTO.tipoDeFila);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new FilaResponseDTO(filaRepository.save(fila)));
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<Fila> filaOptional = filaRepository.findById(id);

        if(filaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fila não encontrada!");
        }else{
            filaRepository.delete(filaOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
