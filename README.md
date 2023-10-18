

The MS-Auth is a microservice developed with Spring Boot and Maven. This service provides authentication functionalities for the system.

## Prerequisites

- Docker and Docker Compose installed on your machine.

## Getting Started

To get the microservice up and running locally, follow these steps:

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/your-repository-link/ms-auth.git
   cd ms-auth


```mermaid
graph TD

  Konga -->|Admin Connection| Kong
  MSAuth -->|API Request| Kong
  Kong -->|Logs| Sentry
  MSAuth -->|Logs| Sentry

  subgraph "Microservices Infrastructure"
    Kong
    MSAuth[MS Auth]
  end

  subgraph "Monitoring & Admin"
    Konga
    Sentry
  end

