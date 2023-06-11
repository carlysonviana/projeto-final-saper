package com.saper.clinicalotus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saper.clinicalotus.model.Funcionario;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

    Optional<Funcionario> findByLogin(String username);
}
