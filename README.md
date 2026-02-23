# UaiPedidos

Sistema fullstack de gerenciamento de pedidos com deploy containerizado em Kubernetes.

> ### Video Explicativo
> https://youtu.be/p0f_RxCD5tk

## Descrição
O UaiPedidos é uma aplicação fullstack para gerenciamento de pedidos,
desenvolvida com foco em boas práticas de arquitetura (DDD e Clean Architecture),
containerização com Docker e orquestração com Kubernetes.

O projeto cobre todo o fluxo, desde o desenvolvimento do backend e frontend
até o deploy em ambiente containerizado.


## 🚀 Funcionalidades

- Criar pedido com múltiplos itens
- Cálculo automático do valor total
- Aprovar pedido
- Cancelar pedido
- Excluir pedido concluído
- Listar todos os pedidos
- Consultar pedido por ID

## 🛠 Tecnologias

### Backend
- Java 21
- Spring Boot 4
- JPA / Hibernate
- PostgreSQL 16

### Frontend
- Angular 17
- TypeScript

### Arquitetura
- DDD
- Clean Architecture

### DevOps
- Docker
- Kubernetes

## 🏗 Arquitetura

Frontend (Angular)  
↓  
Nginx  
↓  
Backend (Spring Boot)  
↓  
PostgreSQL  

## ▶️ Como executar o projeto

### Docker + Kubernetes

1. Build das imagens
2. Aplicar os manifests:
   kubectl apply -f k8s/
3. Acessar:
   http://localhost:30090