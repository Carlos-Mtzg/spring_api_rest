# Spring API REST

## Introduction

This project is a simple RESTful API built with Spring Boot. It provides basic CRUD operations for user management, following best practices for clean architecture and validation. The API is designed for educational and starter project purposes.

---

## Requirements

- Java 17+
- Maven 3.8+

---

## Configuration

All configuration is managed via the `src/main/resources/application.properties` file.  
You should set up your database connection and other environment-specific properties here.

**Example:**

```properties
# Database configuration
db.host=localhost
db.port=3306
db.name=spring_api_rest
db.username=your_username
db.password=your_password


# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true
```

---

## Running the project

1. **Clone the repository:**
   ```sh
   git clone https://github.com/your-username/spring_api_rest.git
   cd spring_api_rest
   ```

2. **Configure your `application.properties`** as shown above.

3. **Build and run the project:**
   ```sh
   ./mvnw spring-boot:run
   ```
   or
   ```sh
   mvn spring-boot:run
   ```

4. The API will be available at [http://localhost:8080](http://localhost:8080).

---

## Main Endpoints

| Method | Endpoint                | Description                  |
|--------|------------------------ |------------------------------|
| GET    | `/api/v1/user`          | List all users               |
| GET    | `/api/v1/user/{uuid}`   | Get user by UUID             |
| POST   | `/api/v1/user`          | Create a new user            |
| PUT    | `/api/v1/user/{uuid}`   | Update user by UUID          |
| DELETE | `/api/v1/user/{uuid}`   | Delete user by UUID          |

**All endpoints return JSON responses.**

---