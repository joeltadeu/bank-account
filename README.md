# Bank account using CQRS, Event Sourcing, Kafka

Learn to build distributed Event-driven Microservices, CQRS, Event Sourcing using Kafka, MySQL and MongoDB

## There are two microservices:

- **Account Query** : This microservice is responsible for read information about the accounts using the read database.
- **Account Command** : This microservice is responsible for managing accounts. A user can open an account, deposit funds and withdraw funds.

### Concepts used ###
- Event-Driven Microservices
- CQRS Design Pattern
- Event Based Messages
- Kafka
- NoSQL database with MongoDB

### EndPoints ###

| Service         | EndPoint                            | Method | Description           |
|-----------------|-------------------------------------|:------:|-----------------------|
| Account Command | /api/v1/accounts                    |  POST  | Open an account       |
| Account Command | /api/v1/accounts/{id}/deposit       |  PUT   | Deposit funds         |
| Account Command | /api/v1/accounts/{id}/withdraw      |  PUT   | Withdraw funds        |
| Account Command | /api/v1/accounts/{id}               | DELETE | Close an account      |
| Account Command | /api/v1/operations/database/restore |  POST  | Restore read database |
| Account Query   | /api/v1/accounts                    |  GET   | List of accounts      |
| Account Query   | /api/v1/accounts/{id}               |  GET   | LIst of accounts      |

### Documentation and examples ###

###Postman collection

![Alt text](assets/postman-collection-folder.png?raw=true "Postman collection folder")

## Build & Run

- *>mvn clean package* : to build
- *>docker-compose up* --build : build docker images and containers and run containers
- *>docker-compose stop* : stop the dockerized services
- Each maven module has a Dockerfile.

In docker-compose.yml file:

- Account Command : **__5000__** port is mapped to **__5000__** port of host
- Account Query : **__5001__** port is mapped to **__5001__** port of host

## VERSIONS

### 1.0.0

- MongoDB
- MySQL
- Spring-Boot 2.6.2.RELEASE
- Java 16