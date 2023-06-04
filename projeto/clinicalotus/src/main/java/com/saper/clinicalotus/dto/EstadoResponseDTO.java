package com.saper.clinicalotus.dto;
import com.saper.clinicalotus.model.Cidade;
import com.saper.clinicalotus.model.Estado;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class EstadoResponseDTO {
    public Long id;
    public String nome;
    public List<Long> cidades;
    public EstadoResponseDTO(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
        Set<Cidade> cidades = estado.getCidades();
        if(cidades != null){
            this.cidades = estado.getCidades().stream().map(cidade -> cidade.getId()).toList();
        }
        else{
            this.cidades = Collections.emptyList();
        }
    }
}
