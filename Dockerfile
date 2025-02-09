# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app


COPY target/FoodFast-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que tu aplicación utilizará
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]