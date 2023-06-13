package com.saper.clinicalotus.service;


import com.saper.clinicalotus.model.Funcionario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MyService {



    public Funcionario getLoggedFuncionario() {
        return (Funcionario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
