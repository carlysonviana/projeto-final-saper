package com.saper.clinicalotus.repository;
import com.saper.clinicalotus.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p WHERE " +
            "(:id is null or p.id = :id) " +
            "and (:nome is null or p.nome = :nome "+
            "or SUBSTRING(p.nome, 1, LOCATE(' ', p.nome) - 1) = :nome) "+
            "and (:cpf is null or p.cpf = :cpf) " +
            "and (:email is null or p.email = :email) " +
            "and (:dataNascimento is null or p.dataNascimento = :dataNascimento)")
    List<Paciente> findAllByParameters(Long id, String nome, String cpf, String email, LocalDate dataNascimento);

    List<Paciente> findByNomeStartingWithIgnoreCase(String primeiroNome);

}

