package com.saper.clinicalotus.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.saper.clinicalotus.dto.EspecialidadeRequestDTO;
import com.saper.clinicalotus.service.EspecialidadeService;

@RestController
@RequestMapping("/especialidade")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EspecialidadeController {
    @Autowired
    EspecialidadeService especialidadeService;

    @GetMapping
    public Object getAll(){
        return especialidadeService.getAll();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id){
        return especialidadeService.findById(id);
    }

    @PostMapping
    public Object save(@Valid @RequestBody EspecialidadeRequestDTO especialidadeRequestDTO){
        return especialidadeService.save(especialidadeRequestDTO);
    }
    
    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,@Valid
                         @RequestBody EspecialidadeRequestDTO especialidadeRequestDTO){
        return especialidadeService.update(id, especialidadeRequestDTO);                        
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return especialidadeService.delete(id);
    }
}
