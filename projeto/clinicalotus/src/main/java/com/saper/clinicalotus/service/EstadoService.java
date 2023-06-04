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
        Optional<Estado> estadoOptional = estadoRepository.findById(id);

        if(estadoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não encontrada!");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new EstadoResponseDTO(estadoOptional.get()));
        }
    }
    @Transactional
    public Object update(Long id, EstadoRequestDTO EstadoRequestDTO) {
        Optional<Estado> estadoOptional = estadoRepository.findById(id);

        if(estadoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não encontrada!");
        }else{
            Estado estado = estadoOptional.get();

            if(EstadoRequestDTO.nome != null){
                estado.setNome(EstadoRequestDTO.nome);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new EstadoResponseDTO(estadoRepository.save(estado)));
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<Estado> estadoOptional = estadoRepository.findById(id);

        if(estadoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não encontrada!");
        }else{
            estadoRepository.delete(estadoOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }


}
