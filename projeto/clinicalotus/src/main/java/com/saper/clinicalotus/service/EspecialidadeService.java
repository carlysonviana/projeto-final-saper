package com.saper.clinicalotus.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.saper.clinicalotus.dto.EspecialidadeRequestDTO;
import com.saper.clinicalotus.dto.EspecialidadeResponseDTO;
import com.saper.clinicalotus.model.Especialidade;
import com.saper.clinicalotus.repository.EspecialidadeRepository;

import jakarta.transaction.Transactional;

@Service
public class EspecialidadeService {

    @Autowired
    EspecialidadeRepository especialidadeRepository;

    @Transactional
    public ResponseEntity<Object> save(EspecialidadeRequestDTO especialidadeRequestDTO){

        Especialidade especialidade = new Especialidade();
        especialidade.setNome(especialidadeRequestDTO.nome);
        especialidade.setDescricao(especialidadeRequestDTO.descricao);

        especialidadeRepository.save(especialidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EspecialidadeResponseDTO(especialidade));
    }

    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(especialidadeRepository.findAll()
                             .stream().map((especialidade -> new EspecialidadeResponseDTO(especialidade))));
    }
    
    public ResponseEntity<Object> findById(Long id) {
        Optional<Especialidade> especialidadeOptional = especialidadeRepository.findById(id);

        if(especialidadeOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade não encontrada!");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new EspecialidadeResponseDTO(especialidadeOptional.get()));
        }
    }
    @Transactional
    public Object update(Long id, EspecialidadeRequestDTO especialidadeRequestDTO) {
        Optional<Especialidade> especialidadeOptional = especialidadeRepository.findById(id);

        if(especialidadeOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade não encontrada!");
        }else{
            Especialidade especialidade = especialidadeOptional.get();

            if(especialidadeRequestDTO.nome != null){
                especialidade.setNome(especialidadeRequestDTO.nome);
            }

            if(especialidadeRequestDTO.descricao != null){
                especialidade.setDescricao(especialidadeRequestDTO.descricao);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new EspecialidadeResponseDTO(especialidadeRepository.save(especialidade)));
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<Especialidade> especialidadeOptional = especialidadeRepository.findById(id);

        if(especialidadeOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade não encontrada!");
        }else{
            especialidadeRepository.delete(especialidadeOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
    
}
