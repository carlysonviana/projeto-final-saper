package com.saper.clinicalotus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saper.clinicalotus.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
    
}
