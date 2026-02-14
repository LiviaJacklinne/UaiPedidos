package com.inatel.cdg.uaipedidos.infra.persistence;

import com.inatel.cdg.uaipedidos.domain.pedido.ItemPedido;
import com.inatel.cdg.uaipedidos.domain.pedido.Pedido;
import com.inatel.cdg.uaipedidos.infra.persistence.entity.ItemPedidoJpaEntity;
import com.inatel.cdg.uaipedidos.infra.persistence.entity.PedidoJpaEntity;

import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoJpaEntity toJpa(Pedido pedido) {
        PedidoJpaEntity entity = new PedidoJpaEntity();
        entity.setId(pedido.getId());
        entity.setCriadoEm(pedido.getCriadoEm());
        entity.setStatus(pedido.getStatus());

        var itens = pedido.getItens().stream().map(item -> {
            ItemPedidoJpaEntity itemEntity = new ItemPedidoJpaEntity();
            itemEntity.setNomeProduto(item.getNomeProduto());
            itemEntity.setQuantidade(item.getQuantidade());
            itemEntity.setPrecoUnitario(item.getPrecoUnitario());
            itemEntity.setPedido(entity);
            return itemEntity;
        }).collect(Collectors.toList());

        entity.setItens(itens);

        return entity;
    }

    public static Pedido fromJpa(PedidoJpaEntity entity) {

        var itensDominio = entity.getItens().stream()
                .map(itemEntity -> new ItemPedido(
                        itemEntity.getNomeProduto(),
                        itemEntity.getQuantidade(),
                        itemEntity.getPrecoUnitario()
                ))
                .toList();

        return new Pedido(
                entity.getId(),
                entity.getCriadoEm(),
                entity.getStatus(),
                itensDominio
        );
    }

}
