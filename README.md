# Dockerfile
```bash
# Backend api/
docker build -t uaipedidos-api .

# Sempre que altera backend
docker compose up --build

# Frontend ui/
docker build -t uaipedidos-ui .
docker run -p 4200:80 uaipedidos-ui

# Rebuildar frontend
docker build -t liviajacklinne/uaipedidos-ui:2.2 .
docker push liviajacklinne/uaipedidos-ui:2.2

docker build -t liviajacklinne/uaipedidos-api:1.2 .
docker push liviajacklinne/uaipedidos-api:1.2
```

Subir docker-compose
```bash
docker compose up --build
```


# Kubernetes

```yml
# Você pode definir o namespace padrão do seu contexto:
kubectl config set-context --current --namespace=uaipedidos
```

```bash
# criando cluster
kubectl create namespace uaipedidos

kubectl apply -f infra/k8s/backend-deployment.yaml


kubectl apply -f infra/k8s/postgres-deployment.yaml
kubectl apply -f infra/k8s/postgres-service.yaml


kubectl apply -f infra/k8s/frontend-deployment.yaml
kubectl apply -f infra/k8s/frontend-service.yaml



```

# cURL backend
```
curl -X POST http://localhost:8080/pedidos \
     -H "Content-Type: application/json" \
     -d '{
           "itens": [
             {
               "nomeProduto": "Café",
               "quantidade": 3,
               "precoUnitario": 5
             }
           ]
         }'

```