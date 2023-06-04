package com.saper.clinicalotus.dto;

import com.saper.clinicalotus.model.Endereco;

public class EnderecoResponseDTO {

    public Long id;
    public String logradouro;
    public Long numero;
    public String bairro;
    public String complemento;
    public String cep;

    public Long cidade_id;
    public Long estado_id;

    public EnderecoResponseDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.complemento = endereco.getComplemento();
        this.cep = endereco.getCep();
        this.cidade_id = endereco.getCidade().getId();
        this.estado_id = endereco.getCidade().getEstado().getId();
    }

    
}
