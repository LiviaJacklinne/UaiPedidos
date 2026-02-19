# ---------- STAGE 1: Build ----------
FROM node:20 AS build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

RUN npm run build


# ---------- STAGE 2: Nginx ----------
FROM nginx:alpine

# Remove config padrão
RUN rm /etc/nginx/conf.d/default.conf

# Copia config personalizada
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copia build Angular
COPY --from=build /app/dist/ui/browser /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
