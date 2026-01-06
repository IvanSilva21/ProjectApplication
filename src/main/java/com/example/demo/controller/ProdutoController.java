package com.example.demo.controller;

import com.example.demo.domain.Produto;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.service.ProdutoService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/produtos")

public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @PostMapping
    public Produto AdicionarProdutos(@RequestBody Produto produto) {
        return produtoService.adicionarProdutos(produto);
    }
}
