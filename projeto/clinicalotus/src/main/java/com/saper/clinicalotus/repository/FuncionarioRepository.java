package com.saper.clinicalotus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saper.clinicalotus.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    
}
