package com.saper.clinicalotus.dto;

import com.saper.clinicalotus.model.CategoriaFuncionario;

public class CategoriaFuncionarioResponseDTO {
    
    public Long categoriaFuncionario_id;
    public String nome;
    public String descricao;
    public boolean ativo;

    public CategoriaFuncionarioResponseDTO(CategoriaFuncionario categoria) {
        this.categoriaFuncionario_id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
        this.ativo = categoria.isAtivo();
    }

    
    
}
