package com.saper.clinicalotus.controller;

import com.saper.clinicalotus.dto.FuncionarioResponseDTO;
import com.saper.clinicalotus.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MyController {

    @Autowired
    MyService myService;

    @GetMapping("/funcionario")
    public ResponseEntity<Object> getFuncionario(){
        return ResponseEntity.status(HttpStatus.OK).body( new FuncionarioResponseDTO(myService.getLoggedFuncionario()));
    }
}
