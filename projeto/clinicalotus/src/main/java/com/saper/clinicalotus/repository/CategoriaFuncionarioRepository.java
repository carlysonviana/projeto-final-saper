package com.saper.clinicalotus.repository;

import com.saper.clinicalotus.model.CategoriaFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaFuncionarioRepository extends JpaRepository<CategoriaFuncionario, Long> {
    
}
