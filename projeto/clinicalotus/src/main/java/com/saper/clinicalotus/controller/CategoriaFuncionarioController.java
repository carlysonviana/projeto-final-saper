package com.saper.clinicalotus.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saper.clinicalotus.dto.CategoriaFuncionarioRequestDTO;
import com.saper.clinicalotus.service.CategoriaFuncionarioService;

@RestController
@RequestMapping("/categoriaFuncionario")
public class CategoriaFuncionarioController {
    @Autowired
    CategoriaFuncionarioService categoriaFuncionarioService;

    @GetMapping
    public Object getAll(){
        return categoriaFuncionarioService.getAll();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id){
        return categoriaFuncionarioService.findById(id);
    }

    @PostMapping
    public Object save(@Valid @RequestBody CategoriaFuncionarioRequestDTO categoriaFuncionarioRequestDTO){
        return categoriaFuncionarioService.save(categoriaFuncionarioRequestDTO);
    }
    
    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,@Valid
                         @RequestBody CategoriaFuncionarioRequestDTO categoriaFuncionarioRequestDTO){
        return categoriaFuncionarioService.update(id, categoriaFuncionarioRequestDTO);                        
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return categoriaFuncionarioService.delete(id);
    }
}
