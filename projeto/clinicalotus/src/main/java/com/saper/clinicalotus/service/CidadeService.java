package com.saper.clinicalotus.service;
import com.saper.clinicalotus.dto.CidadeRequestDTO;
import com.saper.clinicalotus.dto.CidadeResponseDTO;
import com.saper.clinicalotus.model.Cidade;
import com.saper.clinicalotus.model.Estado;
import com.saper.clinicalotus.repository.CidadeRepository;
import com.saper.clinicalotus.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Transactional
    public ResponseEntity<Object> save(CidadeRequestDTO cidadeRequestDTO){

        Optional<Estado> optionalEstado = estadoRepository.findById(cidadeRequestDTO.estado_id);

        if(optionalEstado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não encontrado!");
        }

        Cidade cidade = new Cidade();
        cidade.setNome(cidadeRequestDTO.nome);
        cidade.setEstado(optionalEstado.get());

        cidadeRepository.save(cidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CidadeResponseDTO(cidade));
    }

    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeRepository.findAll()
                .stream().map((cidade -> new CidadeResponseDTO(cidade))));
    }

    public ResponseEntity<Object> findById(Long id) {
        Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);

        if(cidadeOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade não encontrada!");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new CidadeResponseDTO(cidadeOptional.get()));
        }
    }
    @Transactional
    public Object update(Long id, CidadeRequestDTO cidadeRequestDTO) {
        Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);

        if(cidadeOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade não encontrada!");
        }else{
            Cidade cidade = cidadeOptional.get();

            if(cidadeRequestDTO.nome != null){
                cidade.setNome(cidadeRequestDTO.nome);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new CidadeResponseDTO(cidadeRepository.save(cidade)));
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);

        if(cidadeOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade não encontrada!");
        }else{
            cidadeRepository.delete(cidadeOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
