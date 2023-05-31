package com.saper.clinicalotus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saper.clinicalotus.model.Consulta;
import com.saper.clinicalotus.model.Paciente;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PacienteResponseDTO {

    public long id;
    public String nome;

    public String cpf;

    public String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dataNascimento;
    public List<Long> consultas;
    public PacienteResponseDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.cpf = paciente.getCpf();
        this.email = paciente.getEmail();
        this.dataNascimento = paciente.getDataNascimento();
        Set<Consulta> consultas = paciente.getConsultas();
        if(consultas != null){
            this.consultas = paciente.getConsultas().stream().map(consulta -> consulta.getId()).toList();
        }
        else{
            this.consultas = Collections.emptyList();
        }
    }
}
