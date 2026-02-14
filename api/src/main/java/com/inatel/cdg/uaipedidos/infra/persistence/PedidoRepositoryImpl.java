package com.inatel.cdg.uaipedidos.infra.persistence;

import com.inatel.cdg.uaipedidos.domain.pedido.Pedido;
import com.inatel.cdg.uaipedidos.domain.pedido.PedidoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    private final SpringDataPedidoRepository springRepository;

    public PedidoRepositoryImpl(SpringDataPedidoRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        var entity = PedidoMapper.toJpa(pedido);
        springRepository.save(entity);
        return pedido;
    }

    @Override
    public Optional<Pedido> buscarPorId(UUID id) {
        return springRepository.buscarPorIdComItens(id)
                .map(PedidoMapper::fromJpa);
    }

    @Override
    public List<Pedido> listarTodos() {
        return springRepository.buscarTodosComItens()
                .stream()
                .map(PedidoMapper::fromJpa)
                .toList();
    }

    @Override
    public void deletar(UUID id) {
        springRepository.deleteById(id);
    }
}
