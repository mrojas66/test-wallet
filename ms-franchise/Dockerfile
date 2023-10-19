# Fase de construcción
FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY . ./
# Instala Maven (ajusta la versión según lo necesites)
RUN apt-get update && apt-get install -y maven
# Construye la aplicación
RUN mvn clean package -DskipTests

# Fase de ejecución
FROM tomcat:10-jdk17
LABEL MAINTAINER="michaelrojas@progracol.com"
# Eliminar las aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*
# Copiar tu WAR a la carpeta de aplicaciones de Tomcat
COPY --from=build /app/target/ms-franchise-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/
# Establecer el perfil de Spring Boot
#ENV SPRING_PROFILES_ACTIVE=dev
# Exponer el puerto por defecto de Tomcat
EXPOSE 8080
# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]