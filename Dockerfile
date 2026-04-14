# Usa imagen base con Java 17 (o 11 según tu proyecto)
FROM eclipse-temurin:17-jdk-jammy

# Directorio dentro del contenedor
WORKDIR /app

# Copiar el jar
COPY target/transporte-api-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto
EXPOSE 8080

# Ejecutar app
ENTRYPOINT ["java", "-jar", "app.jar"]