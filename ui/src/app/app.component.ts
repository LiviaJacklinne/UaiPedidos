import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PedidosComponent } from './pages/pedidos/pedidos.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    PedidosComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ui';
}
