package com.inatel.cdg.uaipedidos.application.mapper;

import com.inatel.cdg.uaipedidos.application.dto.PedidoResponseDto;
import com.inatel.cdg.uaipedidos.domain.pedido.Pedido;

public class PedidoResponseMapper {

    public static PedidoResponseDto toResponse(Pedido pedido) {

        var itens = pedido.getItens().stream()
                .map(item -> new PedidoResponseDto.ItemResponse(
                        item.getNomeProduto(),
                        item.getQuantidade(),
                        item.getPrecoUnitario(),
                        item.getSubtotal()
                ))
                .toList();

        return new PedidoResponseDto(
                pedido.getId(),
                pedido.getCriadoEm(),
                pedido.getStatus().name(),
                pedido.getTotal(),
                itens
        );
    }

}
