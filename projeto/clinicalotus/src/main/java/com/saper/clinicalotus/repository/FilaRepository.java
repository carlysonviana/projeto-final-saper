package com.saper.clinicalotus.repository;


import com.saper.clinicalotus.model.Fila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Long> {
}
