package com.example.demo.controller;


import com.example.demo.domain.Produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private List<Produto> produtos = new ArrayList<>();

    @GetMapping
    public List<Produto> listarProdutos(){
        return produtos;
    }

    @PostMapping
    public Produto AdicionarProduto(@RequestBody Produto produto) {
        produtos.add(produto);
        return produto;
    }
}
