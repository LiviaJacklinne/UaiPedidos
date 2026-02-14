package com.inatel.cdg.uaipedidos.application.usecase.pedido;

import com.inatel.cdg.uaipedidos.domain.pedido.Pedido;
import com.inatel.cdg.uaipedidos.domain.pedido.PedidoNaoEncontradoException;
import com.inatel.cdg.uaipedidos.domain.pedido.PedidoRepository;

import java.util.UUID;

public class BuscarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public BuscarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido executar(UUID id) {
        return pedidoRepository.buscarPorId(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id.toString()));
    }
}

