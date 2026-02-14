package com.inatel.cdg.uaipedidos.application.usecase.pedido;

import com.inatel.cdg.uaipedidos.application.dto.CriarPedidoInputDto;
import com.inatel.cdg.uaipedidos.domain.pedido.ItemPedido;
import com.inatel.cdg.uaipedidos.domain.pedido.Pedido;
import com.inatel.cdg.uaipedidos.domain.pedido.PedidoRepository;

public class CriarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public CriarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido executar(CriarPedidoInputDto input) {

        if (input.itens() == null || input.itens().isEmpty()) {
            throw new IllegalArgumentException("Pedido deve possuir pelo menos um item");
        }

        Pedido pedido = new Pedido();

        input.itens().forEach(itemInput -> {
            ItemPedido item = new ItemPedido(
                    itemInput.nomeProduto(),
                    itemInput.quantidade(),
                    itemInput.precoUnitario()
            );

            pedido.adicionarItem(item);
        });

        return pedidoRepository.salvar(pedido);
    }

}
