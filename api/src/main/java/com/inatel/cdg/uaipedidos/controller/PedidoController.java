package com.inatel.cdg.uaipedidos.controller;

import com.inatel.cdg.uaipedidos.application.dto.CriarPedidoInputDto;
import com.inatel.cdg.uaipedidos.application.dto.PedidoResponseDto;
import com.inatel.cdg.uaipedidos.application.mapper.PedidoResponseMapper;
import com.inatel.cdg.uaipedidos.application.usecase.pedido.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final ListarTodosPedidosUseCase listarTodosPedidosUseCase;
    private final AprovarPedidoUseCase aprovarPedidoUseCase;
    private final CancelarPedidoUseCase cancelarPedidoUseCase;
    private final DeletarPedidoUseCase deletarPedidoUseCase;

    public PedidoController(CriarPedidoUseCase criarPedidoUseCase, BuscarPedidoUseCase buscarPedidoUseCase,
                            ListarTodosPedidosUseCase listarTodosPedidosUseCase, CancelarPedidoUseCase cancelarPedidoUseCase,
                            AprovarPedidoUseCase aprovarPedidoUseCase, DeletarPedidoUseCase deletarPedidoUseCase) {
        this.buscarPedidoUseCase = buscarPedidoUseCase;
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.listarTodosPedidosUseCase = listarTodosPedidosUseCase;
        this.aprovarPedidoUseCase = aprovarPedidoUseCase;
        this.cancelarPedidoUseCase = cancelarPedidoUseCase;
        this.deletarPedidoUseCase = deletarPedidoUseCase;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDto> criar(@RequestBody CriarPedidoInputDto input) {

        var pedido = criarPedidoUseCase.executar(input);
        var response = PedidoResponseMapper.toResponse(pedido);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDto> buscar(@PathVariable UUID id) {

        var pedido = buscarPedidoUseCase.executar(id);

        return ResponseEntity.ok(PedidoResponseMapper.toResponse(pedido));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> listarTodos() {

        var pedidos = listarTodosPedidosUseCase.executar();

        var response = pedidos.stream()
                .map(PedidoResponseMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/aprovar")
    public ResponseEntity<Void> aprovar(@PathVariable UUID id) {

        aprovarPedidoUseCase.executar(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable UUID id) {

        cancelarPedidoUseCase.executar(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {

        deletarPedidoUseCase.executar(id);

        return ResponseEntity.noContent().build();
    }
}
