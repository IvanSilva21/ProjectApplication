package com.example.demo.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;


public class ProdutoRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "Categoria é obrigatória")
    private Long categoriaId;

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

}


