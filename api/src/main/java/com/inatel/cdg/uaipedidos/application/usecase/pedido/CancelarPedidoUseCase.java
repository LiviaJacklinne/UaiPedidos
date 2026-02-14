package com.inatel.cdg.uaipedidos.application.usecase.pedido;

import com.inatel.cdg.uaipedidos.domain.pedido.PedidoNaoEncontradoException;
import com.inatel.cdg.uaipedidos.domain.pedido.PedidoRepository;

import java.util.UUID;

public class CancelarPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    public CancelarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void executar(UUID id) {

        var pedido = pedidoRepository.buscarPorId(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id.toString()));

        pedido.cancelar(); // 🔥 regra no domínio

        pedidoRepository.salvar(pedido);
    }
}
