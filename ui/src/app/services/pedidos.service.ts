import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { NovoPedido, Pedido } from '../modules/pedido/pedido.module';


@Injectable({
  providedIn: 'root'
})
export class PedidosService {

  private baseUrl = `${environment.apiUrl}/pedidos`;

  constructor(private http: HttpClient) {}

  // GET ALL
  getAll(): Observable<Pedido[]> {
    return this.http.get<Pedido[]>(this.baseUrl);
  }

  // GET BY ID
  getById(id: string): Observable<Pedido> {
    return this.http.get<Pedido>(`${this.baseUrl}/${id}`);
  }

  // CREATE
  create(pedido: NovoPedido): Observable<Pedido> {
    return this.http.post<Pedido>(this.baseUrl, pedido);
  }

  // APROVAR
  aprovar(id: string): Observable<void> {
    return this.http.patch<void>(`${this.baseUrl}/${id}/aprovar`, {});
  }

  // CANCELAR
  cancelar(id: string): Observable<void> {
    return this.http.patch<void>(`${this.baseUrl}/${id}/cancelar`, {});
  }

  // DELETE
  delete(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
