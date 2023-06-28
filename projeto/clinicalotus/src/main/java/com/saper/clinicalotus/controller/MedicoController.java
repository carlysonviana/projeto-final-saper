package com.saper.clinicalotus.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.saper.clinicalotus.dto.MedicoRequestDTO;
import com.saper.clinicalotus.service.MedicoService;

@RestController
@RequestMapping("/funcionario/medico")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicoController {
    @Autowired
    MedicoService medicoService;

    @GetMapping
    public Object getAll(){
        return medicoService.getAll();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id){
        return medicoService.findById(id);
    }
    
    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,@Valid
                         @RequestBody MedicoRequestDTO medicoRequestDTO){
        return medicoService.update(id, medicoRequestDTO);                        
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return medicoService.delete(id);
    }
}
