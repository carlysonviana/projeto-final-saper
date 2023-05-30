package com.saper.clinicalotus.controller;

import com.saper.clinicalotus.dto.PacienteRequestDTO;
import com.saper.clinicalotus.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

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
