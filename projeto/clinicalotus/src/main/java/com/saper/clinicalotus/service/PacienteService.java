package com.saper.clinicalotus.service;
import com.saper.clinicalotus.dto.PacienteRequestDTO;
import com.saper.clinicalotus.dto.PacienteResponseDTO;
import com.saper.clinicalotus.model.Endereco;
import com.saper.clinicalotus.model.Paciente;
import com.saper.clinicalotus.repository.EnderecoRepository;
import com.saper.clinicalotus.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public Object getAll(){
        return pacienteRepository.findAll().stream().map((paciente -> new PacienteResponseDTO(paciente)));
    }

    @Transactional
    public Object save(PacienteRequestDTO pacienteRequestDTO){
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(pacienteRequestDTO.endereco_id);

        if(optionalEndereco.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado!");
        }

        Paciente paciente = new Paciente();
        paciente.setCpf(pacienteRequestDTO.cpf);
        paciente.setNome(pacienteRequestDTO.nome);
        paciente.setEmail(pacienteRequestDTO.email);
        paciente.setDataNascimento(pacienteRequestDTO.dataNascimento);
        paciente.setEndereco(optionalEndereco.get());

        pacienteRepository.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PacienteResponseDTO(paciente));
    }

    public ResponseEntity<Object> findById(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

        if(pacienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new PacienteResponseDTO(pacienteOptional.get()));
        }
    }

    @Transactional
    public Object update(Long id, PacienteRequestDTO pacienteRequestDTO) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

        if(pacienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado!");
        }else{
            Paciente paciente = pacienteOptional.get();

            if(pacienteRequestDTO.nome != null){
                paciente.setNome(pacienteRequestDTO.nome);
            }
            if(pacienteRequestDTO.email != null){
                paciente.setEmail(pacienteRequestDTO.email);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new PacienteResponseDTO(pacienteRepository.save(paciente)));
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

        if(pacienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado!");
        }else{
            pacienteRepository.delete(pacienteOptional.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    public ResponseEntity<Object> getAllByParameters(Long pacienteId, String nome, String cpf, String email, LocalDate dataNascimento, Long enderecoId) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.findAllByParameters(pacienteId, nome, cpf, email, dataNascimento, enderecoId).stream().map((PacienteResponseDTO::new)));
    }

    public ResponseEntity<Object> getAllByPrimeiroNome(String primeiroNome) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.findByNomeStartingWithIgnoreCase(primeiroNome).stream().map((PacienteResponseDTO::new)));
    }
}
