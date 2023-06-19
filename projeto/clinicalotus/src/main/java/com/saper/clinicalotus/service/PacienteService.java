package com.saper.clinicalotus.service;
import com.saper.clinicalotus.dto.PacienteRequestDTO;
import com.saper.clinicalotus.dto.PacienteResponseDTO;
import com.saper.clinicalotus.model.Endereco;
import com.saper.clinicalotus.model.Paciente;
import com.saper.clinicalotus.model.PlanoDeSaude;
import com.saper.clinicalotus.repository.EnderecoRepository;
import com.saper.clinicalotus.repository.PacienteRepository;
import com.saper.clinicalotus.repository.PlanoDeSaudeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    PlanoDeSaudeRepository planoDeSaudeRepository;

    public Object getAll(){
        return pacienteRepository.findAll().stream().map((paciente -> new PacienteResponseDTO(paciente)));
    }

    @Transactional
    public Object save(PacienteRequestDTO pacienteRequestDTO){
        Endereco endereco;
        PlanoDeSaude planoDeSaude;

        Paciente paciente = new Paciente();

        if(pacienteRequestDTO.endereco_id != null){
            endereco = enderecoRepository.findById(pacienteRequestDTO.endereco_id).orElseThrow(()-> new NoSuchElementException("Endereco não encontrado!"));
            paciente.setEndereco(endereco);
        }

        if(pacienteRequestDTO.plano_id != null){
            planoDeSaude = planoDeSaudeRepository.findById(pacienteRequestDTO.plano_id).orElseThrow(()-> new NoSuchElementException("Plano não encontrado!"));
            paciente.setPlanoDeSaude(planoDeSaude);
        }

        paciente.setCpf(pacienteRequestDTO.cpf);
        paciente.setNome(pacienteRequestDTO.nome);
        paciente.setEmail(pacienteRequestDTO.email);
        paciente.setDataNascimento(pacienteRequestDTO.dataNascimento);

        pacienteRepository.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PacienteResponseDTO(paciente));
    }

    public ResponseEntity<Object> findById(Long id) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Paciente não encontrado!"));

            return ResponseEntity.status(HttpStatus.OK).body(new PacienteResponseDTO(paciente));

    }

    @Transactional
    public Object update(Long id, PacienteRequestDTO pacienteRequestDTO) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Paciente não encontrado!"));

            if(pacienteRequestDTO.nome != null){
                paciente.setNome(pacienteRequestDTO.nome);
            }
            if(pacienteRequestDTO.email != null){
                paciente.setEmail(pacienteRequestDTO.email);
            }
            if(pacienteRequestDTO.dataNascimento != null){
                paciente.setDataNascimento(pacienteRequestDTO.dataNascimento);
            }
            if(pacienteRequestDTO.plano_id != null){
                Optional<PlanoDeSaude> planoDeSaudeOptional = planoDeSaudeRepository.findById(pacienteRequestDTO.plano_id);
                paciente.setPlanoDeSaude(planoDeSaudeOptional.get());
            }
            
            return ResponseEntity.status(HttpStatus.OK).body(new PacienteResponseDTO(pacienteRepository.save(paciente)));
    }

    public ResponseEntity<Object> delete(Long id) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Paciente não encontrado!"));

            pacienteRepository.delete(paciente);
            return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Object> getAllByParameters(Long pacienteId, String nome, String cpf, String email, LocalDate dataNascimento, Long enderecoId) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.findAllByParameters(pacienteId, nome, cpf, email, dataNascimento, enderecoId).stream().map((PacienteResponseDTO::new)));
    }

    public ResponseEntity<Object> getAllByPrimeiroNome(String primeiroNome) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.findByNomeStartingWithIgnoreCase(primeiroNome).stream().map((PacienteResponseDTO::new)));
    }
}
