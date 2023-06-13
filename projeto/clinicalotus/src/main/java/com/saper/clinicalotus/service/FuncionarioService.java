package com.saper.clinicalotus.service;
import com.saper.clinicalotus.dto.FuncionarioRequestDTO;
import com.saper.clinicalotus.dto.FuncionarioResponseDTO;
import com.saper.clinicalotus.enums.RoleNames;
import com.saper.clinicalotus.model.CategoriaFuncionario;
import com.saper.clinicalotus.model.Funcionario;
import com.saper.clinicalotus.model.Role;
import com.saper.clinicalotus.repository.CategoriaFuncionarioRepository;
import com.saper.clinicalotus.repository.FuncionarioRepository;
import com.saper.clinicalotus.repository.MedicoRepository;

import com.saper.clinicalotus.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    CategoriaFuncionarioRepository categoriaFuncionarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public Object save(FuncionarioRequestDTO funcionarioRequestDTO){
        Funcionario funcionario = new Funcionario(funcionarioRequestDTO);

        Optional<CategoriaFuncionario> categorOptional =  categoriaFuncionarioRepository.findById(funcionarioRequestDTO.categoriaFuncionario_id);

        funcionario.setCategoriaFuncionario(categorOptional.get());
        funcionario = funcionarioRepository.save(funcionario);

        if (categorOptional.get().getNome().equals("Médico")) {
            setRoleAsMedico(funcionario);
        } else if (categorOptional.get().getNome().equals("Recepcionista")) {
            setRoleAsRecepcionista(funcionario);
        }

        FuncionarioResponseDTO funcionarioResponseDTO = new FuncionarioResponseDTO(funcionario);

        return funcionarioResponseDTO;
    }

    public void setRoleAsMedico(Funcionario funcionario) {
        Optional<Role> optionalRole = roleRepository.findByRole(RoleNames.ROLE_MEDICO);
        Set<Role> setRole = new HashSet<>();
        setRole.add(optionalRole.get());
        funcionario.setRoles(setRole);
    }

    public void setRoleAsRecepcionista(Funcionario funcionario) {
        Optional<Role> optionalRole = roleRepository.findByRole(RoleNames.ROLE_RECEPCIONISTA);
        Set<Role> setRole = new HashSet<>();
        setRole.add(optionalRole.get());
        funcionario.setRoles(setRole);
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

