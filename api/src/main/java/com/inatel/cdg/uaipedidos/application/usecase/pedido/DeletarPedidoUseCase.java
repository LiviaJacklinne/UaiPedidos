package com.inatel.cdg.uaipedidos.application.usecase.pedido;

import com.inatel.cdg.uaipedidos.domain.pedido.PedidoNaoEncontradoException;
import com.inatel.cdg.uaipedidos.domain.pedido.PedidoRepository;

import java.util.UUID;

public class DeletarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public DeletarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void executar(UUID id) {
        if (!pedidoRepository.buscarPorId(id).isPresent()) {
            throw new PedidoNaoEncontradoException(id.toString());
        }

        pedidoRepository.deletar(id);
    }
}
