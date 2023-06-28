package com.saper.clinicalotus.controller;

import com.saper.clinicalotus.dto.PacienteRequestDTO;
import com.saper.clinicalotus.dto.PlanoDeSaudeRequestDTO;
import com.saper.clinicalotus.model.PlanoDeSaude;
import com.saper.clinicalotus.service.PlanoDeSaudeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planoDeSaude")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanoDeSaudeController {

    @Autowired
    PlanoDeSaudeService planoDeSaudeService;

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id) {
        return planoDeSaudeService.findById(id);
    }

    @GetMapping
    public Object getAll() {
        return planoDeSaudeService.getAll();
    }

    @PostMapping
    public Object save(@Valid @RequestBody PlanoDeSaudeRequestDTO planoDeSaudeRequestDTO){
        return planoDeSaudeService.save(planoDeSaudeRequestDTO);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,@Valid @RequestBody PlanoDeSaudeRequestDTO planoDeSaudeRequestDTO){

        return planoDeSaudeService.update(id, planoDeSaudeRequestDTO);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return planoDeSaudeService.delete(id);
    }
}

