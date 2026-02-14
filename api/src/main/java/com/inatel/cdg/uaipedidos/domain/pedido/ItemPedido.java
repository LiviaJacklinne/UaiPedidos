package com.inatel.cdg.uaipedidos.domain.pedido;

import java.math.BigDecimal;

public class ItemPedido {

    private final String nomeProduto;
    private final Integer quantidade;
    private final BigDecimal precoUnitario; // BigDecimal é recomendado para valores monetários

    public ItemPedido(String nomeProduto, Integer quantidade, BigDecimal precoUnitario) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (precoUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }

        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getSubtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }
}
