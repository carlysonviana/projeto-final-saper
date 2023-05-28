package com.saper.clinicalotus.controller;

import com.saper.clinicalotus.dto.ConsultaRequestDTO;
import com.saper.clinicalotus.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

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
    public Object save(@RequestBody ConsultaRequestDTO teamRequestDTO){
        return consultaService.save(teamRequestDTO);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,
                         @RequestBody ConsultaRequestDTO consultaRequestDTO){

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
