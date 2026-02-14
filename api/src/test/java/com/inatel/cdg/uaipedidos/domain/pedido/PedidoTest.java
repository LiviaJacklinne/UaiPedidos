package com.inatel.cdg.uaipedidos.domain.pedido;

import com.inatel.cdg.uaipedidos.domain.shared.RegraDeNegocioException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PedidoTest {

    @Test
    void deveAprovarPedidoQuandoEstiverCriadoEComItens() {

        var item = new ItemPedido("Café",2, new BigDecimal("10.00"));

        var pedido = Pedido.criar();
        pedido.adicionarItem(item);
        pedido.aprovar();

        assertEquals(StatusPedido.APROVADO, pedido.getStatus());
    }

    @Test
    void naoDeveAprovarSeStatusNaoForCriado() {

        var item = new ItemPedido("Café",1, new BigDecimal("10.00"));

        var pedido = Pedido.criar();
        pedido.adicionarItem(item);
        pedido.aprovar(); // primeira vez OK

        var exception = assertThrows(RegraDeNegocioException.class, pedido::aprovar);

        assertEquals("Somente pedidos criados podem ser aprovados", exception.getMessage());
    }

    @Test
    void naoDeveAprovarSemItens() {

        var pedido = Pedido.criar();
        var exception = assertThrows(RegraDeNegocioException.class, pedido::aprovar);

        assertEquals("Pedido não pode ser aprovado sem itens", exception.getMessage());
    }

    @Test
    void naoDeveCancelarPedidoAprovado() {

        var item = new ItemPedido("Café",1, new BigDecimal("10.00"));

        var pedido = Pedido.criar();
        pedido.adicionarItem(item);
        pedido.aprovar();

        var exception = assertThrows(RegraDeNegocioException.class, pedido::cancelar);

        assertEquals("Pedido aprovado não pode ser cancelado",exception.getMessage());
    }

    @Test
    void deveCancelarPedidoCriado() {

        var item = new ItemPedido("Café",1, new BigDecimal("10.00"));

        var pedido = Pedido.criar();
        pedido.adicionarItem(item);
        pedido.cancelar();

        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
    }
}