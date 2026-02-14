package com.inatel.cdg.uaipedidos.application.usecase.pedido;

import com.inatel.cdg.uaipedidos.domain.pedido.Pedido;
import com.inatel.cdg.uaipedidos.domain.pedido.PedidoRepository;

import java.util.List;

public class ListarTodosPedidosUseCase {

    private final PedidoRepository pedidoRepository;

    public ListarTodosPedidosUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> executar() {
        return pedidoRepository.listarTodos();
    }
}
