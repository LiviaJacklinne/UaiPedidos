package com.inatel.cdg.uaipedidos.infra.persistence;

import com.inatel.cdg.uaipedidos.infra.persistence.entity.PedidoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataPedidoRepository extends JpaRepository<PedidoJpaEntity, UUID> {

    @Query(" SELECT p FROM PedidoJpaEntity p LEFT JOIN FETCH p.itens ")
    List<PedidoJpaEntity> buscarTodosComItens();

    @Query("SELECT p FROM PedidoJpaEntity p LEFT JOIN FETCH p.itens WHERE p.id = :id")
    Optional<PedidoJpaEntity> buscarPorIdComItens(@Param("id") UUID id);
}
