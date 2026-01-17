package com.example.demo.service;

import com.example.demo.domain.Categoria;
import com.example.demo.dto.update.ProdutoUpdateDTO;
import com.example.demo.exception.ProdutoNotFoundException;
import com.example.demo.domain.Produto;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import com.example.demo.dto.response.ProdutoResponseDTO;
import com.example.demo.dto.request.ProdutoRequestDTO;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final CategoriaRepository categoriaRepository;


    public ProdutoService(ProdutoRepository repository, CategoriaRepository categoriaRepository) {

        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }


    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(categoria);

        Produto salvo = repository.save(produto);

        return new ProdutoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getPreco()
        );
    }

    public class CategoriaNotFoundException extends RuntimeException {
        public CategoriaNotFoundException(Long id) {
            super("Categoria não encontrada com id: " + id);
        }
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
