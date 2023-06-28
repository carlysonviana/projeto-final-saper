package com.saper.clinicalotus.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.saper.clinicalotus.dto.FuncionarioRequestDTO;
import com.saper.clinicalotus.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FuncionarioController {
    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping
    public Object getAll(){
        return funcionarioService.getAll();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id){
        return funcionarioService.findById(id);
    }

    @PostMapping
    public Object save(@Valid @RequestBody FuncionarioRequestDTO funcionarioRequestDTO){
        return funcionarioService.save(funcionarioRequestDTO);
    }
    
    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,
                         @RequestBody FuncionarioRequestDTO funcionarioRequestDTO){
        return funcionarioService.update(id, funcionarioRequestDTO);                        
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return funcionarioService.delete(id);
    }
}
