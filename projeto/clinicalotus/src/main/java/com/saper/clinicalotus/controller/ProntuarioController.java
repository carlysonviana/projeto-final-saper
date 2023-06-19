package com.saper.clinicalotus.controller;

import com.saper.clinicalotus.dto.ProntuarioRequestDTO;
import com.saper.clinicalotus.service.ProntuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {

    @Autowired
    ProntuarioService prontuarioService;

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id) {
        return prontuarioService.findById(id);
    }

    @GetMapping
    public Object getAll() {
        return prontuarioService.getAll();
    }

    @PostMapping
    public Object save(@Valid @RequestBody ProntuarioRequestDTO prontuarioRequestDTO){
        return prontuarioService.save(prontuarioRequestDTO);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,@Valid @RequestBody ProntuarioRequestDTO prontuarioRequestDTO){

        return prontuarioService.update(id, prontuarioRequestDTO);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return prontuarioService.delete(id);
    }
}
