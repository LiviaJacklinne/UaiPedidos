package com.inatel.cdg.uaipedidos.domain.pedido;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException(String id) {
        super("Pedido com id " + id + " não encontrado");
    }
}
