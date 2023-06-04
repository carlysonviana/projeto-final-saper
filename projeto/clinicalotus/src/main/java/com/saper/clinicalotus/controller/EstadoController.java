package com.saper.clinicalotus.controller;

import com.saper.clinicalotus.dto.EstadoRequestDTO;
import com.saper.clinicalotus.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estado")
public class EstadoController {
    @Autowired
    EstadoService estadoService;

    @GetMapping
    public Object getAll(){
        return estadoService.getAll();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id){
        return estadoService.findById(id);
    }

    @PostMapping
    public Object save(@RequestBody EstadoRequestDTO estadoRequestDTO){
        return estadoService.save(estadoRequestDTO);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,
                         @RequestBody EstadoRequestDTO estadoRequestDTO){
        return estadoService.update(id, estadoRequestDTO);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return estadoService.delete(id);
    }

}
