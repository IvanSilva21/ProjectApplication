package com.example.demo.domain;

import lombok.Data;

//
@Data
public class Produto {

    //Atributos
    private String nome;
    private double preco;

    //Contrutor Vazio
    public Produto(){

    }
    //Contrutor normal
    public Produto(String nome, Double preco){
        this.nome = nome;
        this.preco = preco;
    }
}
