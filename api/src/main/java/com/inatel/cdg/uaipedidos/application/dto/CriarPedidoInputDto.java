package com.inatel.cdg.uaipedidos.application.dto;

import java.math.BigDecimal;
import java.util.List;

public record CriarPedidoInputDto(List<ItemInput> itens) {

    public record ItemInput(
            String nomeProduto,
            Integer quantidade,
            BigDecimal precoUnitario
    ) {}
}
