package com.inatel.cdg.uaipedidos.application.usecase.pedido;

import com.inatel.cdg.uaipedidos.domain.pedido.PedidoNaoEncontradoException;
import com.inatel.cdg.uaipedidos.domain.pedido.PedidoRepository;

import java.util.UUID;

public class AprovarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public AprovarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void executar(UUID id) {

        var pedido = pedidoRepository.buscarPorId(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id.toString()));

        pedido.aprovar();
        pedidoRepository.salvar(pedido);
    }
}
