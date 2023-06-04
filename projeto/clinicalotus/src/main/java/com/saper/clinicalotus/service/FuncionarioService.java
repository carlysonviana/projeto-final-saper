package com.saper.clinicalotus.service;
import com.saper.clinicalotus.dto.FuncionarioRequestDTO;
import com.saper.clinicalotus.dto.FuncionarioResponseDTO;
import com.saper.clinicalotus.model.Funcionario;
import com.saper.clinicalotus.model.Medico;
import com.saper.clinicalotus.repository.FuncionarioRepository;
import com.saper.clinicalotus.repository.MedicoRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public Object save(FuncionarioRequestDTO funcionarioRequestDTO){
        Funcionario funcionario = new Funcionario(funcionarioRequestDTO);
        
        funcionario = funcionarioRepository.save(funcionario);
        if (funcionarioRequestDTO.categoriaFuncionario.getId() == 2){
            Medico medico = new Medico();
            medico.setFuncionario(funcionario);
            medicoRepository.save(medico);
        }

        FuncionarioResponseDTO funcionarioResponseDTO = new FuncionarioResponseDTO(funcionario);

        return funcionarioResponseDTO;
    }

    public Object getAll(){
        return funcionarioRepository.findAll().stream().map((funcionario -> new FuncionarioResponseDTO(funcionario)));
    }

    public ResponseEntity<Object> findById(Long id) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);

        if(funcionarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new FuncionarioResponseDTO(funcionarioOptional.get()));
        }
    }

    @Transactional
    public Object update(Long id, FuncionarioRequestDTO funcionarioRequestDTO) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);

        if(funcionarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado!");
        }else{
            Funcionario funcionario = funcionarioOptional.get();

            if(funcionarioRequestDTO.nome != null){
                funcionario.setNome(funcionarioRequestDTO.nome);
            }
            if(funcionarioRequestDTO.email != null){
                funcionario.setEmail(funcionarioRequestDTO.email);
            }
            if(funcionarioRequestDTO.telefone != null){
                funcionario.setTelefone(funcionarioRequestDTO.telefone);
            }
            if(funcionarioRequestDTO.celular != null){
                funcionario.setCelular(funcionarioRequestDTO.celular);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new FuncionarioResponseDTO(funcionarioRepository.save(funcionario)));
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);

        if(funcionarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não encontrado!");
        }else{
            funcionarioRepository.delete(funcionarioOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
