# ZVote-SpringBoot

## Table of Contents
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Architecture](#architecture)
4. [Getting Started](#getting-started)
5. [Prerequisites](#prerequisites)
6. [Installation](#installation)
7. [Usage](#usage)
8. [API Endpoints](#api-endpoints)

---

## Project Overview
**ZVote-SpringBoot** is the backend Spring Boot version for ZVote, providing a RESTful API for electronic voting operations. This backend is designed for scalability, security, and ease of integration with various front-end clients (including JavaFX and web UIs). It supports poll creation, voting, and result retrieval with robust data management.

---

## Features
- User authentication and authorization.
- Poll creation and management via RESTful endpoints.
- Casting votes for poll options.
- Real-time result querying.
- CRUD operations for polls, options, and votes.
- Secure endpoints for sensitive operations.
- Persistent storage using JPA/Hibernate with a relational database.

---

## Architecture
ZVote-SpringBoot follows standard Spring Boot architecture:

- **Controllers**: REST endpoints for poll, vote, and user operations.
- **Services**: Business logic for poll and voting workflows.
- **Repositories**: JPA interfaces for database access.
- **Entities**: JPA/Hibernate models for polls, votes, users, etc.

---

## Getting Started
Instructions to set up and run the ZVote-SpringBoot backend locally.

### Prerequisites
- **Java JDK**: Version 11 or newer.
- **Maven**: For dependency management and building.
- **Git**: To clone the repository.
- **Database**: MySQL, PostgreSQL, H2, or another supported database.

---

## Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/MateoBitar/ZVote-SpringBoot.git
    cd ZVote-SpringBoot
    ```

2. **Configure application properties**:
    - Edit `src/main/resources/application.properties` to set your database connection:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/zvote
      spring.datasource.username=your_db_user
      spring.datasource.password=your_db_password
      spring.jpa.hibernate.ddl-auto=update
      server.port=8080
      ```
    - Update other settings as needed (JPA, security, etc.).

3. **Build and run the project**:
    ```bash
    mvn spring-boot:run
    ```
    Or, build a JAR and run:
    ```bash
    mvn clean package
    java -jar target/zvote-springboot-*.jar
    ```

---

## Usage

- Once running, the backend exposes RESTful API endpoints (see [API Endpoints](#api-endpoints)).
- Connect your front-end application (JavaFX or web client) to the backend via HTTP.
- Use Swagger UI (if configured) at `http://localhost:8080/swagger-ui/` for interactive API exploration.

---

## API Endpoints

Common endpoints include:
- `POST /api/polls` - Create a new poll
- `GET /api/polls` - List polls
- `GET /api/polls/{id}` - Get poll details
- `POST /api/polls/{id}/vote` - Cast a vote on a poll
- `GET /api/polls/{id}/results` - Get poll results
- `POST /api/auth/register` - Register new user (if implemented)
- `POST /api/auth/login` - Authenticate user (if implemented)

Refer to the code or Swagger documentation for full details.

---

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request. For major changes, open an issue first to discuss your proposals.

---

## License

This project is licensed under the MIT License.

---

For additional questions or issues, please refer to the documentation or contact the repository maintainer.
