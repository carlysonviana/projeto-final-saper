package com.saper.clinicalotus.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.saper.clinicalotus.exception.exceptions.ConflictStoreException;
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

        try {
            especialidadeRepository.save(especialidade);
        }catch (Exception exception){
            throw  new ConflictStoreException("Não foi possivel salvar sua especialidade!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new EspecialidadeResponseDTO(especialidade));
    }

    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(especialidadeRepository.findAll()
                             .stream().map((especialidade -> new EspecialidadeResponseDTO(especialidade))));
    }
    
    public ResponseEntity<Object> findById(Long id) {
        Especialidade especialidade = especialidadeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Especialidade não encontrada!"));

            return ResponseEntity.status(HttpStatus.OK).body(new EspecialidadeResponseDTO(especialidade));
    }
    @Transactional
    public Object update(Long id, EspecialidadeRequestDTO especialidadeRequestDTO) {
        Especialidade especialidade = especialidadeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Especialidade não encontrada!"));

            if(especialidadeRequestDTO.nome != null){
                especialidade.setNome(especialidadeRequestDTO.nome);
            }

            if(especialidadeRequestDTO.descricao != null){
                especialidade.setDescricao(especialidadeRequestDTO.descricao);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new EspecialidadeResponseDTO(especialidadeRepository.save(especialidade)));
    }

    public ResponseEntity<Object> delete(Long id) {
        Especialidade especialidade = especialidadeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Especialidade não encontrada!"));

            try {
                especialidadeRepository.delete(especialidade);
            }catch (Exception exception){
                throw  new ConflictStoreException("Não foi possivel deletar a especialidade!");
            }
            return ResponseEntity.status(HttpStatus.OK).build();

    }
    
}
