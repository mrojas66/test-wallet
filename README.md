# MS - Wallet Example

The MS-Auth is a microservice developed with Spring Boot and Maven. This service provides authentication functionalities for the system.

Prerequisites:
- Docker and Docker Compose installed on your machine.

Services:
This microservice architecture utilizes the following services:
1. MS-Auth: The main authentication microservice developed with Spring Boot.
2. PostgreSQL: A database service for MS-Auth.
3. Sentry: A tool for real-time error tracking that gives your team insight into errors.
4. Kong: An API gateway used for handling API requests.
5. Konga: A GUI for Kong to manage your APIs.

Getting Started:
To get the microservice up and running locally, follow these steps:

1. Clone the Repository:
   git clone https://github.com/your-repository-link/ms-auth.git
   cd ms-auth

2. Set Up Environment Variables:
   Before running the services, you need to set up the environment variables. Create a .env file in the root directory and add the necessary environment variables. Refer to the docker-compose.yml for the environment variables required.

3. Build and Run with Docker Compose:
   docker-compose up --build

4. Access the Services:
   - MS-Auth: Accessible on http://localhost:8081
   - Sentry UI: Accessible on http://localhost:9000
   - Konga (Kong Dashboard): Accessible on http://localhost:1337
   - Kong Admin API: Accessible on http://localhost:8001

5. Stopping the Services:
   To stop the services, you can use:
   docker-compose down

Additional Notes:
Ensure that the ports defined in the docker-compose.yml are free on your machine. If there are conflicts, you can modify the ports accordingly.

Feel free to contribute and raise issues if you face any. Enjoy developing!

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

