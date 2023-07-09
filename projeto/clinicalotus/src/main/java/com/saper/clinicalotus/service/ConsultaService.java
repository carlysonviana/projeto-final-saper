package com.saper.clinicalotus.service;

import com.saper.clinicalotus.dto.ConsultaRequestDTO;
import com.saper.clinicalotus.dto.ConsultaResponseDTO;
import com.saper.clinicalotus.exception.exceptions.ConflictStoreException;
import com.saper.clinicalotus.model.Consulta;
import com.saper.clinicalotus.model.Medico;
import com.saper.clinicalotus.model.Paciente;
import com.saper.clinicalotus.model.Prontuario;
import com.saper.clinicalotus.repository.ConsultaRepository;
import com.saper.clinicalotus.repository.MedicoRepository;
import com.saper.clinicalotus.repository.PacienteRepository;
import com.saper.clinicalotus.repository.ProntuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    ProntuarioRepository prontuarioRepository;
    @Transactional
    public ResponseEntity<Object> save(ConsultaRequestDTO consultaRequestDTO) {
        //Verifcar se paciente existe
        Paciente paciente = pacienteRepository.findById(consultaRequestDTO.paciente_id).orElseThrow(()-> new NoSuchElementException("Paciente não encontrado!"));

        //Verifcar se medico existe
        Medico medico = medicoRepository.findById(consultaRequestDTO.medico_id).orElseThrow(()-> new NoSuchElementException("Médico não encontrado!"));

        Consulta consulta = new Consulta();
        consulta.setDataHora(consultaRequestDTO.dataHora);
        consulta.setAutorizacaoPlano(consultaRequestDTO.autorizacaoPlano);

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);

        if( paciente.getProntuario() == null){
            Prontuario prontuario = new Prontuario();

            paciente.setProntuario(prontuario);

            prontuario.setPaciente(paciente);

            consulta.setProntuario(prontuario);

            prontuarioRepository.save(prontuario);
        }else{
            Prontuario prontuario = prontuarioRepository.findById(paciente.getProntuario().getId()).orElseThrow(() -> new NoSuchElementException("Prontuario não encontrado"));
            consulta.setProntuario(prontuario);
        }

        try {
            consultaRepository.save(consulta);
        }catch (Exception exception){
            throw new ConflictStoreException("Não foi possivel salvar a consulta!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ConsultaResponseDTO(consulta));
    }

    public ResponseEntity<Object> confirmar(Long id) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Consulta não encontrada"));

        consulta.setConfirmada(true);

        try {
            consultaRepository.save(consulta);
        }catch (Exception exception){
            throw new ConflictStoreException("Não foi possivel salvar a consulta!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Consulta %d confirmada!", id));

    }

    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaRepository.findAll().stream().map((ConsultaResponseDTO::new)));
    }

    public ResponseEntity<Object> findById(Long id) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Consulta não encontrada"));

            return ResponseEntity.status(HttpStatus.OK).body(new ConsultaResponseDTO(consulta));
    }
    @Transactional
    public Object update(Long id, ConsultaRequestDTO consultaRequestDTO) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Consulta não encontrada"));

            if(consultaRequestDTO.dataHora != null){
                consulta.setDataHora(consultaRequestDTO.dataHora);
            }
            if(consultaRequestDTO.autorizacaoPlano != consulta.isAutorizacaoPlano()){
                consulta.setAutorizacaoPlano(consultaRequestDTO.autorizacaoPlano);
            }

            return ResponseEntity.status(HttpStatus.OK).body(new ConsultaResponseDTO(consultaRepository.save(consulta)));
    }

    public ResponseEntity<Object> delete(Long id) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Consulta não encontrada"));

        consultaRepository.delete(consulta);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Object>  getAllByPaciente_Id(Long pacienteId) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaRepository.findAllByPaciente_Id(pacienteId).stream().map((ConsultaResponseDTO::new)));
    }

    public ResponseEntity<Object> getAllByParameters(Long consultaId, LocalDateTime dataHora, Boolean autorizacaoPlano, Boolean confirmada, Long pacienteId, Long medicoId) {
        return ResponseEntity.status(HttpStatus.OK).body(consultaRepository.findAllByParameters(consultaId, dataHora, autorizacaoPlano, confirmada, pacienteId, medicoId).stream().map((ConsultaResponseDTO::new)));
    }
}
