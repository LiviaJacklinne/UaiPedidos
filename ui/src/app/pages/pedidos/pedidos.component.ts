import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PedidosService } from '../../services/pedidos.service';
import { NovoPedido, Pedido } from '../../modules/pedido/pedido.module';
import { NotificacaoService } from '../../services/notificacao.service';


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

  itensDisponiveis = [
    { nome: 'Café', preco: 5, quantidade: 0 },
    { nome: 'Pão de Queijo', preco: 7.5, quantidade: 0 },
    { nome: 'Pizza', preco: 40, quantidade: 0 },
    { nome: 'Hambúrguer', preco: 25, quantidade: 0 },
    { nome: 'Refrigerante', preco: 8, quantidade: 0 },
    { nome: 'Batata Frita', preco: 15, quantidade: 0 },
  ];


  constructor(
    private pedidosService: PedidosService,
    private notificacao: NotificacaoService
  ) { }


  ngOnInit(): void {
    this.listarTodos();
  }

  // LISTAR TODOS
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

  // BUSCAR POR ID
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

  limpar() {
    this.searchId = '';
    this.listarTodos();
  }

  // FORMULÁRIO
  abrirFormulario() {
    this.resetarItens();
    this.mostrarFormulario = true;
  }

  cancelarFormulario() {
    this.resetarItens();
    this.mostrarFormulario = false;
  }

  resetarItens() {
    this.itensDisponiveis.forEach(item => item.quantidade = 0);
  }

  aumentarQuantidade(item: any) {
    item.quantidade++;
  }

  diminuirQuantidade(item: any) {
    if (item.quantidade > 0) {
      item.quantidade--;
    }
  }

  calcularTotal(): number {
    return this.itensDisponiveis
      .reduce((total, item) => total + (item.preco * item.quantidade), 0);
  }

  // SALVAR
  salvarPedido() {
    const itensSelecionados = this.itensDisponiveis
      .filter(item => item.quantidade > 0)
      .map(item => ({
        nomeProduto: item.nome,
        quantidade: item.quantidade,
        precoUnitario: item.preco
      }));

    if (itensSelecionados.length === 0) return;

    const novoPedido: NovoPedido = {
      itens: itensSelecionados
    };

    this.pedidosService.create(novoPedido)
      .subscribe({
        next: () => {
          this.notificacao.sucesso('Pedido criado com sucesso!');
          this.listarTodos();
          this.mostrarFormulario = false;
        },
        error: () => {
          this.notificacao.erro('Erro ao criar pedido');
        }
      });

  }

  // APROVAR
  aprovar(id: string) {
    this.pedidosService.aprovar(id)
      .subscribe({
        next: () => {
          this.notificacao.sucesso('Pedido aprovado!');
          this.listarTodos();
        },
        error: () => {
          this.notificacao.erro('Erro ao aprovar pedido');
        }
      });
  }

  // CANCELAR
  cancelar(id: string) {
    this.pedidosService.cancelar(id)
      .subscribe({
        next: () => {
          this.notificacao.aviso('Pedido cancelado!');
          this.listarTodos();
        },
        error: () => {
          this.notificacao.erro('Erro ao cancelar pedido');
        }
      });
  }

  // DELETAR
  deletar(id: string) {
    this.pedidosService.delete(id)
      .subscribe({
        next: () => {
          this.notificacao.info('Pedido deletado!');
          this.listarTodos();
        },
        error: () => {
          this.notificacao.erro('Erro ao deletar pedido');
        }
      });
  }
}
