package com.saper.clinicalotus.controller;

import com.saper.clinicalotus.dto.ConsultaRequestDTO;
import com.saper.clinicalotus.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

    @GetMapping("/busca")
    public Object getAllByParameters(@RequestParam(required = false, name="consultaId") Long consultaId,
                                     @RequestParam(required = false, name="dataHora") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dataHora,
                                     @RequestParam(required = false, name="confirmada") Boolean confirmada,
                                     @RequestParam(required = false, name="autorizacaoPlano") Boolean autorizacaoPlano,
                                     @RequestParam(required = false, name="pacienteId") Long pacienteId){
        return consultaService.getAllByParameters(consultaId, dataHora, confirmada, autorizacaoPlano, pacienteId);
    }
    @GetMapping
    public Object getAll(){
        return consultaService.getAll();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id){
        return consultaService.findById(id);
    }

    @GetMapping("/paciente")
    public Object getAllByPaciente_Id(@RequestParam(name = "id") Long paciente_id){
        return consultaService.getAllByPaciente_Id(paciente_id);
    }

    @PostMapping
    public Object save(@Valid @RequestBody ConsultaRequestDTO teamRequestDTO){
        return consultaService.save(teamRequestDTO);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,
                         @Valid @RequestBody ConsultaRequestDTO consultaRequestDTO){

        return consultaService.update(id, consultaRequestDTO);
    }

    @PostMapping("/confirmar/{id}")
    public Object confirmarPresencaPaciente(@PathVariable(name = "id") Long id){
        return consultaService.confirmar(id);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return consultaService.delete(id);
    }
}
