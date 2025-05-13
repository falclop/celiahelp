# Comandos esenciales para docker compose

## 1. Levantar los servicios definidos en el archivo
docker-compose up

## 2. Levantar los servicios en segundo plano
docker-compose up -d

## 3. Detiene y elimina contenedores, redes y volúmenes
docker-compose down -v

## 4. Contruye las imágenes de los servicios
docker-compose build

## 5. Reinicia los servicios
docker-compose restart

## 6. Detiene los constenedores sin borrar
docker-compose stop

## 7. Inicia los servicios
docker-compose start

## 8. Listar los contenedores en ejecución
docker-compose ps
docker-compose ps -a

## 9. Muestra los logs de todos los servicios o de un contenedor
docker-compose logs
docker-compose logs <nombre o id contenedore>

## 10. Ejecuta un comando dentro de un contenedor
docker-compose exec <contenedor> <comando>