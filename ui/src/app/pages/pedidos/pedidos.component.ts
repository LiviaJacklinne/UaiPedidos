import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PedidosService } from '../../services/pedidos.service';
import { NovoPedido, Pedido } from '../../modules/pedido/pedido.module';

@Component({
  selector: 'app-pedidos',
  standalone: true,
  imports: [
    CommonModule, 
    FormsModule
  ],
  templateUrl: './pedidos.component.html',
  styleUrl: './pedidos.component.css'
})

export class PedidosComponent implements OnInit {

  pedidos: Pedido[] = [];

  searchId?: string;

  mostrarFormulario = false;
  editando = false;

  pedidoForm: Partial<Pedido> = {
    descricao: '',
    valor: 0
  };



  constructor(private pedidosService: PedidosService) {}

  ngOnInit(): void {
    this.listarTodos();
  }

  // =============================
  // LISTAR TODOS
  // =============================
  listarTodos(): void {
    this.pedidosService.getAll().subscribe({
      next: (data) => {
        this.pedidos = data;
      },
      error: (err) => {
        console.error('Erro ao listar pedidos', err);
      }
    });
  }

  // =============================
  // BUSCAR POR ID
  // =============================
  buscarPorId(): void {
    if (!this.searchId) return;

    this.pedidosService.getById(this.searchId).subscribe({
      next: (pedido) => {
        this.pedidos = [pedido];
      },
      error: (err) => {
        console.error('Pedido não encontrado', err);
        this.pedidos = [];
      }
    });
  }

  // =============================
  // ABRIR FORMULÁRIO
  // =============================
  abrirFormulario(): void {
    this.mostrarFormulario = true;
    this.editando = false;
    this.pedidoForm = {
      descricao: '',
      valor: 0
    };
  }

  cancelarFormulario(): void {
    this.mostrarFormulario = false;
  }

  // =============================
  // SALVAR (CREATE ou UPDATE)
  // =============================
  salvar(): void {

  if (this.editando && this.pedidoForm.id) {

    const pedidoAtualizado = {
      descricao: this.pedidoForm.descricao!,
      valor: this.pedidoForm.valor!
    };

    this.pedidosService.update(this.pedidoForm.id, pedidoAtualizado)
      .subscribe({
        next: () => {
          this.listarTodos();
          this.mostrarFormulario = false;
        },
        error: (err) => console.error('Erro ao atualizar', err)
      });

  } else {

    const novoPedido = {
      descricao: this.pedidoForm.descricao!,
      valor: this.pedidoForm.valor!
    };

    this.pedidosService.create(novoPedido)
      .subscribe({
        next: () => {
          this.listarTodos();
          this.mostrarFormulario = false;
        },
        error: (err) => console.error('Erro ao criar', err)
      });
  }
}


  // =============================
  // EDITAR
  // =============================
  editar(pedido: Pedido): void {
    this.editando = true;
    this.mostrarFormulario = true;
    this.pedidoForm = { ...pedido };
  }

  // =============================
  // APROVAR
  // =============================
  aprovar(id: string): void {
    this.pedidosService.aprovar(id).subscribe({
      next: () => this.listarTodos(),
      error: (err) => console.error('Erro ao aprovar', err)
    });
  }

  // =============================
  // CANCELAR
  // =============================
  cancelar(id: string): void {
    this.pedidosService.cancelar(id).subscribe({
      next: () => this.listarTodos(),
      error: (err) => console.error('Erro ao cancelar', err)
    });
  }

  // =============================
  // DELETAR
  // =============================
  deletar(id: string): void {
    this.pedidosService.delete(id).subscribe({
      next: () => this.listarTodos(),
      error: (err) => console.error('Erro ao deletar', err)
    });
  }

}
