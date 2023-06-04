package com.saper.clinicalotus.dto;

import com.saper.clinicalotus.model.Cidade;

public class EnderecoRequestDTO {
    
    public String logradouro;
    public Long numero;
    public String bairro;
    public String complemento;
    public String cep;

    public Cidade cidade;
}
