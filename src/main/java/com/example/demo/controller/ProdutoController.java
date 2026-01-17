package com.example.demo.controller;

import com.example.demo.domain.Produto;
import com.example.demo.dto.update.ProdutoUpdateDTO;
import com.example.demo.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.request.ProdutoRequestDTO;
import com.example.demo.dto.response.ProdutoResponseDTO;
import jakarta.validation.Valid;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Produtos", description = "Gerenciamento de produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }



    @Operation(summary = "Cadastrar um novo produto")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> adicionar(
            @RequestBody @Valid ProdutoRequestDTO dto
    ) {
        return ResponseEntity.ok(service.salvar(dto));
    }


    @Operation(summary = "Listar todos os produtos")
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }


    @Operation(
            summary = "Buscar produto por ID",
            description = "Retorna um produto ou erro 404 se não existir"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = service.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoUpdateDTO dto
    ) {
        Produto atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(new ProdutoResponseDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getPreco()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
