package com.saper.clinicalotus.controller;

import com.saper.clinicalotus.dto.CidadeRequestDTO;
import com.saper.clinicalotus.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidade")
public class CidadeController {
    @Autowired
    CidadeService cidadeService;

    @GetMapping
    public Object getAll(){
        return cidadeService.getAll();
    }

    @GetMapping("/{id}")
    public Object getOne(@PathVariable(name = "id") Long id){
        return cidadeService.findById(id);
    }

    @PostMapping
    public Object save(@RequestBody CidadeRequestDTO cidadeRequestDTO){
        return cidadeService.save(cidadeRequestDTO);
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable(name = "id") Long id,
                         @RequestBody CidadeRequestDTO cidadeRequestDTO){
        return cidadeService.update(id, cidadeRequestDTO);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable(name = "id") Long id){
        return cidadeService.delete(id);
    }

}
