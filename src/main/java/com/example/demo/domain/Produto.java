package com.example.demo.domain;

import lombok.Data;

public class Produto {

    private String nome;
    private Double preco;

    // Construtor vazio (OBRIGATÓRIO)
    public Produto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}

