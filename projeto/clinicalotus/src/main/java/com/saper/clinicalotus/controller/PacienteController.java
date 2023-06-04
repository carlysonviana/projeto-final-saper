package com.saper.clinicalotus.controller;

import com.saper.clinicalotus.dto.PacienteRequestDTO;
import com.saper.clinicalotus.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/busca")
    public Object getAllByParameters(@RequestParam(required = false, name="pacienteId") Long pacienteId,
                                     @RequestParam(required = false, name="nome") String nome,
                                     @RequestParam(required = false, name="cpf") String cpf,
                                     @RequestParam(required = false, name="email") String email,
                                     @RequestParam(required = false, name="dataNascimento") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataNascimento,
                                     @RequestParam(required = false, name="enderecoId") Long enderecoId) {
        return pacienteService.getAllByParameters(pacienteId, nome, cpf, email, dataNascimento, enderecoId);
    }

    @GetMapping("/primeiroNome")
    public Object getAllByPrimeiroNome(@RequestParam(name="primeiroNome") String primeiroNome) {
        return pacienteService.getAllByPrimeiroNome(primeiroNome);
    }

    @GetMapping
    public Object getAll(){
        return pacienteService.getAll();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id){
        return pacienteService.findById(id);
    }

    @PostMapping
    public Object save(@RequestBody PacienteRequestDTO pacienteRequestDTO){

        return pacienteService.save(pacienteRequestDTO);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,
                         @RequestBody PacienteRequestDTO pacienteRequestDTO){

        return pacienteService.update(id, pacienteRequestDTO);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return pacienteService.delete(id);
    }
}
