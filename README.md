Buildar Dockerfile
```bash
# Backend api/
docker build -t uaipedidos-api .

# Sempre que altera backend
docker compose up --build


# Frontend ui/
docker build -t uaipedidos-ui .

docker run -p 4200:80 uaipedidos-ui
```

Subir docker-compose
````

```