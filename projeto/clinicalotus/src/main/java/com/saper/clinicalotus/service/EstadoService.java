package com.saper.clinicalotus.service;
import com.saper.clinicalotus.dto.EstadoRequestDTO;
import com.saper.clinicalotus.dto.EstadoResponseDTO;
import com.saper.clinicalotus.model.Estado;
import com.saper.clinicalotus.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EstadoService {
    @Autowired
    EstadoRepository estadoRepository;

    @Transactional
    public ResponseEntity<Object> save(EstadoRequestDTO estadoRequestDTO){

        Estado estado = new Estado();
        estado.setNome(estadoRequestDTO.nome);

        estadoRepository.save(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EstadoResponseDTO(estado));
    }

    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(estadoRepository.findAll()
                .stream().map((estado -> new EstadoResponseDTO(estado))));
    }

    public ResponseEntity<Object> findById(Long id) {
        Estado estado = estadoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Estado não encontrado!"));

            return ResponseEntity.status(HttpStatus.OK).body(new EstadoResponseDTO(estado));
    }
    @Transactional
    public Object update(Long id, EstadoRequestDTO EstadoRequestDTO) {
        Estado estado = estadoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Estado não encontrado!"));

            if(EstadoRequestDTO.nome != null){
                estado.setNome(EstadoRequestDTO.nome);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new EstadoResponseDTO(estadoRepository.save(estado)));
    }

    public ResponseEntity<Object> delete(Long id) {
        Estado estado = estadoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Estado não encontrado!"));

            estadoRepository.delete(estado);

            return ResponseEntity.status(HttpStatus.OK).build();
        
    }


}
