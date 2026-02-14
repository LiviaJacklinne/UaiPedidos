package com.inatel.cdg.uaipedidos.domain.pedido;

import com.inatel.cdg.uaipedidos.domain.shared.RegraDeNegocioException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Pedido {

    private final UUID id;
    private final LocalDateTime criadoEm;
    private StatusPedido status;
    private final List<ItemPedido> itens;

    public Pedido() {
        this.id = UUID.randomUUID();
        this.criadoEm = LocalDateTime.now();
        this.status = StatusPedido.CRIADO;
        this.itens = new ArrayList<>();
    }

    public Pedido(UUID id, LocalDateTime criadoEm, StatusPedido status, List<ItemPedido> itens) {
        this.id = id;
        this.criadoEm = criadoEm;
        this.status = status;
        this.itens = new ArrayList<>(itens);
    }

    public static Pedido criar() {
        return new Pedido();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    // REGRAS DE NEGÓCIO
    public void adicionarItem(ItemPedido item) {
        validarSePedidoPodeSerModificado();
        this.itens.add(item);
    }

    public void aprovar() {
        if (status != StatusPedido.CRIADO) {
            throw new RegraDeNegocioException("Somente pedidos criados podem ser aprovados");
        }

        if (itens.isEmpty()) {
            throw new RegraDeNegocioException("Pedido não pode ser aprovado sem itens");
        }

        if (getTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Total do pedido deve ser maior que zero");
        }

        this.status = StatusPedido.APROVADO;
    }

    public void cancelar() {
        if (status == StatusPedido.APROVADO) {
            throw new RegraDeNegocioException("Pedido aprovado não pode ser cancelado");
        }

        if (status == StatusPedido.CANCELADO) {
            throw new RegraDeNegocioException("Pedido já está cancelado");
        }

        this.status = StatusPedido.CANCELADO;
    }

    public BigDecimal getTotal() {
        return itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void validarSePedidoPodeSerModificado() {
        if (status != StatusPedido.CRIADO) {
            throw new RegraDeNegocioException("Pedido não pode ser modificado após ser aprovado ou cancelado");
        }
    }
}
