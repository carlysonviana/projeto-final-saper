package com.saper.clinicalotus.service;
import com.saper.clinicalotus.dto.CidadeRequestDTO;
import com.saper.clinicalotus.dto.CidadeResponseDTO;
import com.saper.clinicalotus.exception.exceptions.ConflictStoreException;
import com.saper.clinicalotus.model.Cidade;
import com.saper.clinicalotus.model.Estado;
import com.saper.clinicalotus.repository.CidadeRepository;
import com.saper.clinicalotus.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Transactional
    public ResponseEntity<Object> save(CidadeRequestDTO cidadeRequestDTO){
        Estado estado = estadoRepository.findById(cidadeRequestDTO.estado_id).orElseThrow(()-> new NoSuchElementException("Estado não encontrado!"));

        Cidade cidade = new Cidade();
        cidade.setNome(cidadeRequestDTO.nome);
        cidade.setEstado(estado);

        try {
            cidadeRepository.save(cidade);
        }catch (Exception exception){
            throw  new ConflictStoreException("Não foi possivel salvar a cidade");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new CidadeResponseDTO(cidade));
    }

    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeRepository.findAll()
                .stream().map((cidade -> new CidadeResponseDTO(cidade))));
    }

    public ResponseEntity<Object> findById(Long id) {
        Cidade cidade = cidadeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Cidade não encontrado!"));

            return ResponseEntity.status(HttpStatus.OK).body(new CidadeResponseDTO(cidade));

    }
    @Transactional
    public Object update(Long id, CidadeRequestDTO cidadeRequestDTO) {
        Cidade cidade = cidadeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Cidade não encontrado!"));

            if(cidadeRequestDTO.nome != null){
                cidade.setNome(cidadeRequestDTO.nome);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new CidadeResponseDTO(cidadeRepository.save(cidade)));

    }

    public ResponseEntity<Object> delete(Long id) {
        Cidade cidade = cidadeRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Cidade não encontrado!"));

        try {
            cidadeRepository.delete(cidade);
        }catch (Exception exception) {
            throw  new ConflictStoreException("Não foi possivel deletar a cidade");
        }
            return ResponseEntity.status(HttpStatus.OK).build();

    }
}
