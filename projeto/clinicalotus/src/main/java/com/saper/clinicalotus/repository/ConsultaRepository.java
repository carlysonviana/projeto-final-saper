package com.saper.clinicalotus.repository;

import com.saper.clinicalotus.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findAllByPaciente_Id(Long pacienteId);
}
