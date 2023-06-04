package com.saper.clinicalotus.service;
import com.saper.clinicalotus.dto.EnderecoRequestDTO;
import com.saper.clinicalotus.dto.EnderecoResponseDTO;
import com.saper.clinicalotus.model.Cidade;
import com.saper.clinicalotus.model.Endereco;
import com.saper.clinicalotus.repository.CidadeRepository;
import com.saper.clinicalotus.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    @Transactional
    public ResponseEntity<Object> save(EnderecoRequestDTO enderecoRequestDTO){

        Optional<Cidade> optionalCidade = cidadeRepository.findById(enderecoRequestDTO.cidade.getId());

        if(optionalCidade.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não encontrado!");
        }

        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoRequestDTO.logradouro);
        endereco.setNumero(enderecoRequestDTO.numero);
        endereco.setBairro(enderecoRequestDTO.bairro);
        endereco.setCep(enderecoRequestDTO.cep);
        endereco.setComplemento(enderecoRequestDTO.complemento);
        endereco.setCidade(optionalCidade.get());

        enderecoRepository.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EnderecoResponseDTO(endereco));
    }

    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.findAll()
                .stream().map((endereco -> new EnderecoResponseDTO(endereco))));
    }

    public ResponseEntity<Object> findById(Long id) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);

        if(enderecoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado!");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new EnderecoResponseDTO(enderecoOptional.get()));
        }
    }
    @Transactional
    public Object update(Long id, EnderecoRequestDTO enderecoRequestDTO) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);

        if(enderecoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado!");
        }else{
            Endereco endereco = enderecoOptional.get();

            if(enderecoRequestDTO.logradouro != null){
                endereco.setLogradouro(enderecoRequestDTO.logradouro);
            }

            if(enderecoRequestDTO.numero != null){
                endereco.setNumero(enderecoRequestDTO.numero);
            }

            if(enderecoRequestDTO.bairro != null){
                endereco.setBairro(enderecoRequestDTO.bairro);
            }

            if(enderecoRequestDTO.cep != null){
                endereco.setCep(enderecoRequestDTO.cep);
            }

            if(enderecoRequestDTO.complemento != null){
                endereco.setComplemento(enderecoRequestDTO.complemento);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new EnderecoResponseDTO(enderecoRepository.save(endereco)));
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);

        if(enderecoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado!");
        }else{
            enderecoRepository.delete(enderecoOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
