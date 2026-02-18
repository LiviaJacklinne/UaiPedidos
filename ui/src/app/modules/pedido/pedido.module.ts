export interface Pedido {
  id: string;
  descricao: string;
  valor: number;
  status: 'PENDENTE' | 'APROVADO' | 'CANCELADO';
}

// Tipo para criação
export type NovoPedido = Omit<Pedido, 'id' | 'status'>;

export type AtualizarPedido = Omit<Pedido, 'id' | 'status'>;

