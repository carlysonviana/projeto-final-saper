package com.saper.clinicalotus.service;
import com.saper.clinicalotus.dto.FuncionarioRequestDTO;
import com.saper.clinicalotus.dto.FuncionarioResponseDTO;
import com.saper.clinicalotus.enums.RoleNames;
import com.saper.clinicalotus.model.CategoriaFuncionario;
import com.saper.clinicalotus.model.Funcionario;
import com.saper.clinicalotus.model.Medico;
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
import java.util.NoSuchElementException;
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

        CategoriaFuncionario categoriaFuncionario =  categoriaFuncionarioRepository.findById(funcionarioRequestDTO.categoriaFuncionario_id).orElseThrow(()-> new NoSuchElementException("Categoria não encontrada!"));

        funcionario.setCategoriaFuncionario(categoriaFuncionario);
        funcionario = funcionarioRepository.save(funcionario);

        if (categoriaFuncionario.getNome().equals("Médico")) {
            setRoleAsMedico(funcionario);
            Medico medico = new Medico();
            medico.setFuncionario(funcionario);
            medicoRepository.save(medico);
        } else if (categoriaFuncionario.getNome().equals("Recepcionista")) {
            setRoleAsRecepcionista(funcionario);
        }
        else{
            setRoleAsAdmin(funcionario);
        }

        FuncionarioResponseDTO funcionarioResponseDTO = new FuncionarioResponseDTO(funcionario);

        return funcionarioResponseDTO;
    }

    public void setRoleAsAdmin(Funcionario funcionario) {
        Optional<Role> optionalRole = roleRepository.findByRole(RoleNames.ROLE_ADMIN);
        Set<Role> setRole = new HashSet<>();
        setRole.add(optionalRole.get());
        funcionario.setRoles(setRole);
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
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Funcionario não encontrado!"));

            return ResponseEntity.status(HttpStatus.OK).body(new FuncionarioResponseDTO(funcionario));

    }

    @Transactional
    public Object update(Long id, FuncionarioRequestDTO funcionarioRequestDTO) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Funcionario não encontrado!"));

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

    public ResponseEntity<Object> delete(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Funcionario não encontrado!"));

            funcionarioRepository.delete(funcionario);
            return ResponseEntity.status(HttpStatus.OK).build();
    }
}

