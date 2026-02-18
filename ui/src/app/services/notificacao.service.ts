import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class NotificacaoService {

  constructor(private toastr: ToastrService) {}

  sucesso(mensagem: string, titulo: string = 'Sucesso') {
    this.toastr.success(mensagem, titulo);
  }

  erro(mensagem: string, titulo: string = 'Erro') {
    this.toastr.error(mensagem, titulo);
  }

  aviso(mensagem: string, titulo: string = 'Atenção') {
    this.toastr.warning(mensagem, titulo);
  }

  info(mensagem: string, titulo: string = 'Informação') {
    this.toastr.info(mensagem, titulo);
  }
}
