package com.inatel.cdg.uaipedidos.domain.pedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepository {

    Pedido salvar(Pedido pedido);

    Optional<Pedido> buscarPorId(UUID id);

    List<Pedido> listarTodos();

    void deletar(UUID id);
}
