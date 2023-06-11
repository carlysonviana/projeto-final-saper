package com.saper.clinicalotus.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.saper.clinicalotus.dto.CategoriaFuncionarioRequestDTO;
import com.saper.clinicalotus.dto.CategoriaFuncionarioResponseDTO;
import com.saper.clinicalotus.model.CategoriaFuncionario;
import com.saper.clinicalotus.repository.CategoriaFuncionarioRepository;
import jakarta.transaction.Transactional;

@Service
public class CategoriaFuncionarioService {

    @Autowired
    CategoriaFuncionarioRepository categoriaFuncionarioRepository;

    @Transactional
    public ResponseEntity<Object> save(CategoriaFuncionarioRequestDTO categoriaFuncionarioRequestDTO){

        CategoriaFuncionario categoriaFuncionario = new CategoriaFuncionario();
        categoriaFuncionario.setNome(categoriaFuncionarioRequestDTO.nome);
        categoriaFuncionario.setDescricao(categoriaFuncionarioRequestDTO.descricao);

        categoriaFuncionarioRepository.save(categoriaFuncionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CategoriaFuncionarioResponseDTO(categoriaFuncionario));
    }

    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaFuncionarioRepository.findAll()
                             .stream().map((categoriaFuncionario -> new CategoriaFuncionarioResponseDTO(categoriaFuncionario))));
    }
    
    public ResponseEntity<Object> findById(Long id) {
        Optional<CategoriaFuncionario> categoriaFuncionarioOptional = categoriaFuncionarioRepository.findById(id);

        if(categoriaFuncionarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new CategoriaFuncionarioResponseDTO(categoriaFuncionarioOptional.get()));
        }
    }
    @Transactional
    public Object update(Long id, CategoriaFuncionarioRequestDTO categoriaFuncionarioRequestDTO) {
        Optional<CategoriaFuncionario> categoriaFuncionarioOptional = categoriaFuncionarioRepository.findById(id);

        if(categoriaFuncionarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
        }else{
            CategoriaFuncionario categoriaFuncionario = categoriaFuncionarioOptional.get();

            if(categoriaFuncionarioRequestDTO.nome != null){
                categoriaFuncionario.setNome(categoriaFuncionarioRequestDTO.nome);
            }

            if(categoriaFuncionarioRequestDTO.descricao != null){
                categoriaFuncionario.setDescricao(categoriaFuncionarioRequestDTO.descricao);
            }

            if(categoriaFuncionarioRequestDTO.ativo != categoriaFuncionario.isAtivo()){
                categoriaFuncionario.setAtivo(categoriaFuncionarioRequestDTO.ativo);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new CategoriaFuncionarioResponseDTO(categoriaFuncionarioRepository.save(categoriaFuncionario)));
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<CategoriaFuncionario> categoriaFuncionarioOptional = categoriaFuncionarioRepository.findById(id);

        if(categoriaFuncionarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
        }else{
            categoriaFuncionarioRepository.delete(categoriaFuncionarioOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
