package com.saper.clinicalotus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saper.clinicalotus.dto.EspecialidadeRequestDTO;
import com.saper.clinicalotus.service.EspecialidadeService;

@RestController
@RequestMapping("/especialidade")
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
    public Object save(@RequestBody EspecialidadeRequestDTO especialidadeRequestDTO){
        return especialidadeService.save(especialidadeRequestDTO);
    }
    
    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,
                         @RequestBody EspecialidadeRequestDTO especialidadeRequestDTO){
        return especialidadeService.update(id, especialidadeRequestDTO);                        
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return especialidadeService.delete(id);
    }
}
