package com.saper.clinicalotus.controller;
import com.saper.clinicalotus.dto.HorarioAtendimentoRequestDTO;
import com.saper.clinicalotus.service.HorarioAtendimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/horarioAtendimento")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HorarioAtendimentoController {
    @Autowired
    HorarioAtendimentoService horarioAtendimentoService;

    @GetMapping
    public Object getAll(){
        return horarioAtendimentoService.getAll();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id){
        return horarioAtendimentoService.findById(id);
    }

    @PostMapping
    public Object save(@Valid @RequestBody HorarioAtendimentoRequestDTO horarioAtendimentoRequestDTO){

        return horarioAtendimentoService.save(horarioAtendimentoRequestDTO);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,
                         @Valid @RequestBody HorarioAtendimentoRequestDTO horarioAtendimentoRequestDTO){

        return horarioAtendimentoService.update(id, horarioAtendimentoRequestDTO);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return horarioAtendimentoService.delete(id);
    }
}
