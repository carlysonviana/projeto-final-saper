package com.saper.clinicalotus.controller;

import com.saper.clinicalotus.dto.FilaRequestDTO;
import com.saper.clinicalotus.service.FilaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fila")
public class FilaController {

    @Autowired
    FilaService filaService;

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id) {
        return filaService.findById(id);
    }

    @GetMapping
    public Object getAll() {
        return filaService.getAll();
    }

    @PostMapping
    public Object save(@Valid @RequestBody FilaRequestDTO filaRequestDTO){
        return filaService.save(filaRequestDTO);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id, @Valid @RequestBody FilaRequestDTO filaRequestDTO){

        return filaService.update(id, filaRequestDTO);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return filaService.delete(id);
    }
}
