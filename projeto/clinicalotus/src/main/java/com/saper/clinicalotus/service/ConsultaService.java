package com.saper.clinicalotus.service;

import com.saper.clinicalotus.dto.ConsultaRequestDTO;
import com.saper.clinicalotus.dto.ConsultaResponseDTO;
import com.saper.clinicalotus.model.Consulta;
import com.saper.clinicalotus.model.Paciente;
import com.saper.clinicalotus.repository.ConsultaRepository;
import com.saper.clinicalotus.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Transactional
    public ResponseEntity<Object> save(ConsultaRequestDTO consultaRequestDTO) {
        //Verifcar se paciente existe
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(consultaRequestDTO.paciente_id);

        if(optionalPaciente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado!");
        }

        Consulta consulta = new Consulta();
        consulta.setDataHora(consultaRequestDTO.dataHora);
        consulta.setAutorizacaoPlano(consultaRequestDTO.autorizacaoPlano);
        consulta.setPaciente(optionalPaciente.get());

        consultaRepository.save(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ConsultaResponseDTO(consulta));
    }

    public ResponseEntity<Object> confirmar(Long id) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);

        if(optionalConsulta.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrado!");
        }
        Consulta consulta = optionalConsulta.get();

        consulta.setConfirmada(true);

        consultaRepository.save(consulta);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Consulta %d confirmada!", id));

    }

    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaRepository.findAll().stream().map((ConsultaResponseDTO::new)));
    }

    public ResponseEntity<Object> findById(Long id) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(id);

        if(consultaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada!");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new ConsultaResponseDTO(consultaOptional.get()));
        }
    }
    @Transactional
    public Object update(Long id, ConsultaRequestDTO consultaRequestDTO) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(id);

        if(consultaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada!");
        }else{
            Consulta consulta = consultaOptional.get();

            if(consultaRequestDTO.dataHora != null){
                consulta.setDataHora(consultaRequestDTO.dataHora);
            }
            if(consultaRequestDTO.autorizacaoPlano != consulta.isAutorizacaoPlano()){
                consulta.setAutorizacaoPlano(consultaRequestDTO.autorizacaoPlano);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new ConsultaResponseDTO(consultaRepository.save(consulta)));
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(id);

        if(consultaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada!");
        }else{
            consultaRepository.delete(consultaOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    public ResponseEntity<Object>  getAllByPaciente_Id(Long pacienteId) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaRepository.findAllByPaciente_Id(pacienteId).stream().map((ConsultaResponseDTO::new)));
    }

    public ResponseEntity<Object> getAllByParameters(Long consultaId, LocalDateTime dataHora, Boolean autorizacaoPlano, Boolean confirmada, Long pacienteId) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaRepository.findAllByParameters(consultaId, dataHora, autorizacaoPlano, confirmada, pacienteId).stream().map((ConsultaResponseDTO::new)));
    }
}
