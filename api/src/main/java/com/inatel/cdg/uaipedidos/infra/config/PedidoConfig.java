package com.inatel.cdg.uaipedidos.infra.config;

import com.inatel.cdg.uaipedidos.application.usecase.pedido.BuscarPedidoUseCase;
import com.inatel.cdg.uaipedidos.application.usecase.pedido.CriarPedidoUseCase;
import com.inatel.cdg.uaipedidos.application.usecase.pedido.ListarTodosPedidosUseCase;
import com.inatel.cdg.uaipedidos.domain.pedido.PedidoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfig {

    @Bean
    public CriarPedidoUseCase criarPedidoUseCase(PedidoRepository pedidoRepository) {
        return new CriarPedidoUseCase(pedidoRepository);
    }

    @Bean
    public BuscarPedidoUseCase buscarPedidoUseCase(PedidoRepository pedidoRepository) {
        return new BuscarPedidoUseCase(pedidoRepository);
    }

    @Bean
    public ListarTodosPedidosUseCase listarPedidosUseCase(PedidoRepository pedidoRepository) {
        return new ListarTodosPedidosUseCase(pedidoRepository);
    }
}
