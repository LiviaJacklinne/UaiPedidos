package com.inatel.cdg.uaipedidos.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDto (
        UUID id,
        LocalDateTime criadoEm,
        String status,
        BigDecimal total,
        List<ItemResponse> itens){

    public record ItemResponse(
            String nomeProduto,
            Integer quantidade,
            BigDecimal precoUnitario,
            BigDecimal subtotal
    ) {}
}
