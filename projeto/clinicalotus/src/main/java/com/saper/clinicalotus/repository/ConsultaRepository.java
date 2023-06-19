package com.saper.clinicalotus.repository;

import com.saper.clinicalotus.model.Consulta;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findAllByPaciente_Id(Long pacienteId);

    @Query("SELECT c FROM Consulta c WHERE " +
            "(:id is null or c.id = :id) " +
            "and (:dataHora is null or c.dataHora = :dataHora) " +
            "and (:confirmada is null or c.confirmada = :confirmada) " +
            "and (:autorizacaoPlano is null or c.autorizacaoPlano = :autorizacaoPlano) "+
            "and (:pacienteId is null or c.paciente.id = :pacienteId) "+
            "and (:medicoId is null or c.medico.id = :medicoId)")
    List<Consulta> findAllByParameters(Long id, LocalDateTime dataHora, Boolean confirmada, Boolean autorizacaoPlano, Long pacienteId, Long medicoId);

}
