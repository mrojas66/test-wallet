# Etapa de construcción
FROM maven:3.8.3-openjdk-17-slim AS build

# Directorio de trabajo para la etapa de construcción
WORKDIR /workspace/app

# Copiar el pom.xml y descargar las dependencias para aprovechar el caching de Docker
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Ahora, copiamos el código fuente y construimos la aplicación
COPY src src
RUN mvn -B package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim

# Metadatos como el mantenedor del Dockerfile
LABEL maintainer="your-email@example.com"

# Directorio donde estará la aplicación en el contenedor
WORKDIR /app

# Copiar el archivo jar desde la etapa de construcción
COPY --from=build /workspace/app/target/ms-auth-0.0.1-SNAPSHOT.jar /app/ms-auth.jar

# Comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "/app/ms-auth.jar"]
