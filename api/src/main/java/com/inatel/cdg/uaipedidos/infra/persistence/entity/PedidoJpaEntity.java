package com.inatel.cdg.uaipedidos.infra.persistence.entity;

import com.inatel.cdg.uaipedidos.domain.pedido.StatusPedido;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
public class PedidoJpaEntity {

    @Id
    private UUID id;

    private LocalDateTime criadoEm;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemPedidoJpaEntity> itens = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }

    public List<ItemPedidoJpaEntity> getItens() { return itens; }
    public void setItens(List<ItemPedidoJpaEntity> itens) { this.itens = itens; }
}
