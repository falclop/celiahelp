# 1. Imagen base con JDK para compilar y ejecutar Spring Boot
FROM openjdk:21-jdk-slim

# 2. Directorio de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiar el jar generado al contenedor
COPY target/celiahelp-0.0.1-SNAPSHOT.jar app.jar

# 4. Exponer el puerto de Spring Boot
EXPOSE 8080

# 5. Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]
