package com.example.demo.service;

import com.example.demo.dto.update.ProdutoUpdateDTO;
import com.example.demo.exception.ProdutoNotFoundException;
import com.example.demo.domain.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import com.example.demo.dto.response.ProdutoResponseDTO;
import com.example.demo.dto.request.ProdutoRequestDTO;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;


    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        Produto produto = new Produto(dto.getNome(), dto.getPreco());
        Produto salvo = repository.save(produto);
        return new ProdutoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getPreco()
        );
    }

    public List<ProdutoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(p -> new ProdutoResponseDTO(
                        p.getId(),
                        p.getNome(),
                        p.getPreco()
                ))
                .toList();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
    }


    public Produto atualizar(Long id, ProdutoUpdateDTO dto) {
        Produto produto = buscarPorId(id);
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        return repository.save(produto);
    }


    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
