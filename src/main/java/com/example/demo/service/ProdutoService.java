package com.example.demo.service;

import com.example.demo.domain.Produto;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto adicionarProdutos(Produto produto) {
        produtos.add(produto);
        return produto;
    }
}
