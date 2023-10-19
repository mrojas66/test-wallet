# Microservices Project with Kong API Gateway

## Introduction

This project involves deploying multiple microservices with the aid of Docker Compose and managing the APIs through the use of Kong as the API Gateway. The microservices communicate with independent PostgreSQL databases.

## Project Components

### Microservices

1. **ms-auth**: This microservice handles authentication. It connects to a specific PostgreSQL database to manage authentication information.

2. **ms-franchise**: A microservice responsible for managing franchise information. It has its own PostgreSQL database.

### Databases

1. **db-ms-auth**: PostgreSQL database used by the `ms-auth` microservice for authentication.

2. **db-ms-franchise**: PostgreSQL database for the `ms-franchise` microservice.

### Kong API Gateway

Kong is used as the API Gateway to manage and control access to the microservices. In this project, Kong runs in "db-less" mode, meaning it does not use a database. Instead, it's configured through a YAML file.

## Deployment

Deployment is performed using Docker Compose. All services, databases, and the API Gateway are encapsulated within Docker containers.

To deploy the project:

```bash
docker-compose up -d

This will launch all services, databases, and the API Gateway. The microservices will be available on their respective ports with Kong on port 8000.

##Kong API Gateway

Kong is an open-source API Gateway used to handle API requests to multiple microservices. You can manage and configure Kong using its admin interface at http://localhost:8001 or its GUI interface at http://localhost:8002.

## API Documentation with Swagger
The ms-franchise microservice features API documentation through Swagger. You can access the documentation at the following URL:
http://localhost:7072/docs/swagger-ui/index.html

```mermaid
graph TD
  A[Kong API Gateway] --> B[ms-auth]
  A --> C[ms-franchise]
  B --> D[db-ms-auth PostgreSQL]
  C --> E[db-ms-franchise PostgreSQL]
