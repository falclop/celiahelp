FROM openjdk:21-jdk-slim
WORKDIR /app

# Copia el JAR de tu app
COPY target/celiahelp-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto de Spring Boot
EXPOSE 8080

# Copia el script
COPY backend/scripts/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Comando de inicio con espera a MySQL
CMD ["/wait-for-it.sh", "mysql:3306", "--timeout=30", "--strict", "--", "java", "-jar", "app.jar"]
