export interface Pedido {
  id: string;
  criadoEm: string;
  status: 'CRIADO' | 'APROVADO' | 'CANCELADO';
  total: number;
  itens: ItemPedidoResponse[];
}

export interface ItemPedidoResponse {
  nomeProduto: string;
  quantidade: number;
  precoUnitario: number;
  subtotal: number;
}

// Tipo para criação
export interface NovoPedido {
  itens: ItemPedidoRequest[];
}

export interface ItemPedidoRequest {
  nomeProduto: string;
  quantidade: number;
  precoUnitario: number;
}
