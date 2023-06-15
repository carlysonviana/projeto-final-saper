package com.saper.clinicalotus.controller;

import com.saper.clinicalotus.dto.EnderecoRequestDTO;
import com.saper.clinicalotus.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    EnderecoService enderecoService;

    @GetMapping
    public Object getAll(){
        return enderecoService.getAll();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id){
        return enderecoService.findById(id);
    }

    @PostMapping
    public Object save(@Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO){
        return enderecoService.save(enderecoRequestDTO);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,@Valid
                         @RequestBody EnderecoRequestDTO enderecoRequestDTO){
        return enderecoService.update(id, enderecoRequestDTO);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return enderecoService.delete(id);
    }

}
