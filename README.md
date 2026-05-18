# User Management System

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![License](https://img.shields.io/badge/License-Apache%202.0-green)

A robust and scalable User Management System built with Spring Boot 3. It provides a set of RESTful Web Services for managing users, leveraging modern Java development practices and libraries.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Security](#security)
- [Caching](#caching)
- [Docker Deployment](#docker-deployment)
- [Performance Monitoring](#performance-monitoring)
- [Error Handling](#error-handling)
- [Contributing](#contributing)
- [License](#license)

## Overview

This project is a Spring Boot-based RESTful web service that provides comprehensive user management capabilities. It demonstrates industry best practices for building scalable, secure, and maintainable REST APIs with Spring Boot 3.x.

## Features

- **🔐 Security**: Spring Security integration with JWT (JSON Web Token) authentication
- **🎫 JWT Authentication**: Stateless token-based authentication with secure token generation and validation
- **📝 CRUD Operations**: Complete Create, Read, Update, Delete operations for users
- **✅ Validation**: Input validation using Jakarta Validation framework
- **🗂️ Object Mapping**: Automatic DTO/Entity mapping using MapStruct and ModelMapper
- **💾 Caching**: Redis-based caching for improved performance
- **📚 API Documentation**: Swagger/OpenAPI 3.0 integration with comprehensive API docs
- **🏥 Health Checks**: Spring Boot Actuator for monitoring application health
- **🚨 Exception Handling**: Global exception handling with detailed error responses
- **🐳 Containerization**: Docker & Docker Compose support for easy deployment
- **🔍 Debugging**: Comprehensive logging with SQL query tracking

## Technology Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| Spring Boot | 3.4.5 | Framework |
| Java | 17 | Programming Language |
| Spring Security | Latest | Authentication & Authorization |
| JWT (jjwt) | 0.11.5 | JSON Web Token implementation |
| Spring Data JPA | Latest | ORM & Database Access |
| Hibernate | Latest | JPA Implementation |
| MySQL | 8.0 | Relational Database |
| Redis | Latest | Caching Solution |
| MapStruct | 1.5.5 | DTO Mapping |
| Lombok | 1.18.30 | Boilerplate Reduction |
| SpringDoc OpenAPI | 1.8.0 | API Documentation |
| Spring Boot Actuator | Latest | Monitoring & Management |
| Maven | Latest | Build Tool |
| Docker | Latest | Containerization |

## Project Structure

```
user-management-system/
├── src/
│   ├── main/
│   │   ├── java/net/javaguides/springboot/
│   │   │   ├── SpringbootRestfulWebservicesApplication.java
│   │   │   │
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java
│   │   │   │
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java
│   │   │   │   └── UserController.java
│   │   │   │
│   │   │   ├── service/
│   │   │   │   ├── AuthService.java (Interface)
│   │   │   │   ├── UserService.java (Interface)
│   │   │   │   └── impl/
│   │   │   │       ├── AuthServiceImpl.java 
│   │   │   │       └── UserServiceImpl.java 
│   │   │   │
│   │   │   ├── entity/
│   │   │   │   ├── User.java
│   │   │   │   ├── UserJWT.java
│   │   │   │   └── Role.java
│   │   │   │
│   │   │   ├── dto/
│   │   │   │   ├── LoginDto.java
│   │   │   │   ├── RegisterDto.java
│   │   │   │   ├── JWTAuthResponse.java
│   │   │   │   ├── UserDto.java
│   │   │   │   └── ErrorDetails.java
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── UserJWTRepository.java
│   │   │   │   └── RoleRepository.java
│   │   │   │
│   │   │   ├── security/
│   │   │   │   ├── JwtTokenProvider.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   ├── CustomUserDetailsService.java
│   │   │   │   └── JwtAuthenticationEntryPoint.java
│   │   │   │
│   │   │   ├── mapper/
│   │   │   │   ├── UserMapper.java (Interface)
│   │   │   │   └── AutoUserMapper.java (Generated/Implementation)
│   │   │   │
│   │   │   └── exception/
│   │   │       ├── GlobalExceptionalHandler.java
│   │   │       ├── ErrorDetails.java
│   │   │       ├── ResourceNotFoundException.java
│   │   │       ├── EmailAlreadyExistsException.java
│   │   │       └── UserManagementAPIException.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-prod.properties
│   │
│   └── test/
│       └── java/...
│
├── pom.xml
├── Dockerfile
├── docker-compose.yml
├── mvnw & mvnw.cmd
└── README.md


```

## Prerequisites

- **Java 17 or higher**
- **Maven 3.6.3 or higher** (or use included Maven Wrapper)
- **MySQL 8.0 or higher**
- **Docker & Docker Compose** (optional, for containerized deployment)
- **Git** (for cloning the repository)

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/sumit-dhandar/user-management-system.git
cd user-management-system
```

### 2. Create MySQL Database

```sql
CREATE DATABASE user_management;
```

Or let Hibernate auto-create it with `spring.jpa.hibernate.ddl-auto=update`

### 3. Build the Project

Using Maven Wrapper (no Maven installation required):

```bash
# On Windows
mvnw.cmd clean package

# On Linux/Mac
./mvnw clean package
```

Or with installed Maven:

```bash
mvn clean package
```

## Configuration

### Local Development Setup

Edit `src/main/resources/application.properties`:

```properties
# Application Configuration
spring.application.name=springboot-restful-webservices

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/user_management
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update

# Uncomment to use a custom port
# server.port=9090

# JWT Configuration
app.jwt.secret=your-super-secret-key-min-32-characters-long-for-security
app.jwt.expiration=86400000  # 24 hours in milliseconds
app.jwt.prefix=Bearer 
app.jwt.header=Authorization

# Actuator Configuration
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always

# Redis Cache Configuration
spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379
# spring.redis.password=your_password  # If password protected

# Logging Configuration
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.data.redis=DEBUG
logging.level.org.springframework.security=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# ANSI Colored Output
spring.output.ansi.enabled=ALWAYS
```

### Production Configuration

Create or edit `src/main/resources/application-prod.properties`:

```properties
spring.datasource.url=jdbc:mysql://prod-db-host:3306/user_management
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.hikari.maximum-pool-size=20

# JWT Configuration
app.jwt.secret=${JWT_SECRET}
app.jwt.expiration=${JWT_EXPIRATION:86400000}
app.jwt.prefix=Bearer 
app.jwt.header=Authorization

spring.cache.type=redis
spring.redis.host=${REDIS_HOST}
spring.redis.port=${REDIS_PORT}
logging.level.root=INFO
```

## JWT Authentication Guide

### How JWT Authentication Works

This application uses JSON Web Tokens (JWT) for stateless, secure API authentication:

1. **User Registration**: User creates an account via `/api/auth/register`
2. **User Login**: User authenticates with credentials at `/api/auth/login`
3. **Token Issuance**: Server validates credentials and issues a JWT token
4. **Token Storage**: Client stores token (typically in localStorage or sessionStorage)
5. **Token Usage**: Client includes token in every API request: `Authorization: Bearer {token}`
6. **Token Validation**: Server validates token signature and expiration on each request
7. **Protected Access**: Only valid, non-expired tokens grant access to protected resources

### JWT Token Structure

A JWT token consists of three parts separated by dots:

```
Header.Payload.Signature
```

**Example Token:**
```
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlhdCI6MTYyNDU1MTUxMCwiZXhwIjoxNjI0NjM3OTEwfQ.L4qRZNUzHXVBvM8jLjL4u9xLdHvJN5yLmH7z8c9K3q2
```

**Header**: Contains algorithm and token type
```json
{
  "alg": "HS512",
  "typ": "JWT"
}
```

**Payload**: Contains user claims and metadata
```json
{
  "sub": "john.doe@example.com",
  "iat": 1624551510,
  "exp": 1624637910
}
```

**Signature**: Cryptographically signed verification

### Token Expiration & Refresh

- **Default Expiration**: 24 hours (configurable in `application.properties`)
- **Token Refresh**: Obtain new token by logging in again
- **Expired Token**: Returns 401 Unauthorized response

### Common JWT Errors

| Error | Status | Cause | Solution |
|-------|--------|-------|----------|
| Missing Token | 401 | No Authorization header | Include `Authorization: Bearer {token}` in request |
| Invalid Token | 401 | Tampered or malformed token | Re-login to get valid token |
| Expired Token | 401 | Token expiration time exceeded | Login again to refresh token |
| Invalid Signature | 401 | Token signed with different key | Use token from same server |

### Testing JWT Authentication with cURL

**1. Register User:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "Password123!"
  }'
```

**2. Login User:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "Password123!"
  }'
```

**3. Extract Token from Response and Use in API Call:**
```bash
TOKEN="your_jwt_token_here"

curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer $TOKEN"
```

### Running the Application

#### Local Development

```bash
# Option 1: Using Maven
mvnw.cmd spring-boot:run

# Option 2: After building the JAR
java -jar target/ubuntu-EC2.jar

# Option 3: With specific profile
java -jar target/ubuntu-EC2.jar --spring.profiles.active=prod
```

The application will start on `http://localhost:8080` by default.

### Docker Deployment

**Using Docker Compose (Recommended)**

```bash
# Build and start all services
docker-compose up --build

# Run in background
docker-compose up -d --build

# Stop services
docker-compose down

# View logs
docker-compose logs -f springboot-restful-webservices
```

**Using Docker Only**

```bash
# Build the Docker image
docker build -t springboot-restful-webservices:latest .

# Run the container
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/user_management \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=root \
  springboot-restful-webservices:latest
```

## API Documentation

### Interactive API Documentation

Once the application is running, access the Swagger UI documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html (port may vary based on your configuration).


## API Endpoints

### Base URL
```
http://localhost:8080/api
```

### Authentication Endpoints

#### 1. Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "password": "securePassword123",
  "roles": ["ROLE_USER"]
}
```

**Response (201 Created)**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "message": "User registered successfully"
}
```

---

#### 2. Login User
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "john.doe@example.com",
  "password": "securePassword123"
}
```

**Response (200 OK)**
```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlhdCI6MTYyNDU1MTUxMCwiZXhwIjoxNjI0NjM3OTEwfQ...",
  "tokenType": "Bearer",
  "expiresIn": 86400000
}
```

**Note**: Use the `accessToken` in the Authorization header for subsequent requests

---

### User Management Endpoints

#### Base URL for User Endpoints
```
http://localhost:8080/api/users
```

#### 3. Create User
```http
POST /api/users
Content-Type: application/json
Authorization: Bearer {JWT_TOKEN}

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```

**Response (201 Created)**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```

**Permissions**: Authenticated users with valid JWT token

---

#### 4. Get All Users
```http
GET /api/users
Authorization: Bearer {JWT_TOKEN}
```

**Response (200 OK)**
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  },
  {
    "id": 2,
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane.smith@example.com"
  }
]
```

---

#### 5. Get User by ID
```http
GET /api/users/{id}
Authorization: Bearer {JWT_TOKEN}
```

**Response (200 OK)**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```

---

#### 6. Update User
```http
PUT /api/users/{id}
Content-Type: application/json
Authorization: Bearer {JWT_TOKEN}

{
  "firstName": "John",
  "lastName": "Smith",
  "email": "john.smith@example.com"
}
```

**Response (200 OK)**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Smith",
  "email": "john.smith@example.com"
}
```

**Permissions**: Authenticated users with valid JWT token

---

#### 7. Delete User
```http
DELETE /api/users/{id}
Authorization: Bearer {JWT_TOKEN}
```

**Response (204 No Content)**

**Permissions**: Authenticated users with valid JWT token

---

## Database Schema

### Users Table

```sql
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_email ON users(email);
```

**User Entity Fields**:
- `id`: Primary key, auto-generated
- `firstName`: User's first name (required)
- `lastName`: User's last name (required)
- `email`: User's email address (required, unique)

## Security

### Overview

The application implements Spring Security with JWT (JSON Web Token) authentication for secure, stateless communication:

- **Authentication**: JWT-based token authentication (no sessions)
- **Token Generation**: Secure token generation on login/registration
- **Token Validation**: JWT signature and expiration validation
- **Stateless Architecture**: Each request is independent with token-based identification
- **Protected Resources**: All user endpoints require valid JWT token

### JWT Configuration

Configure JWT in `application.properties`:

```properties
# JWT Configuration
app.jwt.secret=your-secret-key-min-32-characters-long-for-security
app.jwt.expiration=86400000  # Token expiration in milliseconds (24 hours)
app.jwt.prefix=Bearer 
app.jwt.header=Authorization
```

### Authentication Flow

1. **Register**: POST `/api/auth/register` with user credentials
2. **Login**: POST `/api/auth/login` with email and password
3. **Receive Token**: Server returns JWT token in response
4. **Use Token**: Include token in Authorization header: `Authorization: Bearer <token>`
5. **Token Validation**: Each request is validated before processing

### Default Credentials (Development)

For testing purposes, you can configure default test users in the database initialization script.

### Protected Endpoints

| Endpoint | Method | Required Authentication |
|----------|--------|---|
| `/api/users` | POST | JWT Token (Authorization: Bearer {TOKEN}) |
| `/api/users/{id}` | PUT | JWT Token (Authorization: Bearer {TOKEN}) |
| `/api/users/{id}` | DELETE | JWT Token (Authorization: Bearer {TOKEN}) |
| `/api/users` | GET | JWT Token (Authorization: Bearer {TOKEN}) |
| `/api/users/{id}` | GET | JWT Token (Authorization: Bearer {TOKEN}) |
| `/api/auth/register` | POST | Public |
| `/api/auth/login` | POST | Public |

### Security Best Practices

- ✅ Always use HTTPS/TLS in production
- ✅ Store JWT secret key in environment variables, not in code
- ✅ Use strong secret keys (minimum 32 characters)
- ✅ Implement short token expiration times
- ✅ Refresh token mechanism for long-lived sessions
- ✅ Use environment variables for sensitive credentials
- ✅ Enable CORS only for trusted domains
- ✅ Implement rate limiting for authentication endpoints
- ✅ Regular security updates and dependency scanning
- ✅ Never expose JWT secret in logs or error messages

## Caching

### Redis Caching

The application uses Redis for distributed caching to improve performance:

```properties
spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379
```

### Cache Configuration

- Cache is enabled via `@EnableCaching` in the main application class
- Cached entities have configurable TTL (Time To Live)
- Cache invalidation on data modifications

### Performance Impact

Expected improvements:
- **50-70%** reduction in database queries
- **30-50%** faster response times for frequently accessed data

## Docker Deployment

### Docker Compose Services

The `docker-compose.yml` file defines:

1. **MySQL Service** (`mysqldb`)
   - Image: mysql:8.0
   - Port: 3308:3306
   - Auto-initialization of `user_management` database
   - Persistent volume for data

2. **Spring Boot Service** (`springboot-restful-webservices`)
   - Automatic build from Dockerfile
   - Port: 8080:8080
   - Environment-based configuration
   - Depends on MySQL service

### Network

Both services communicate over a custom bridge network: `springboot-mysql-net`

### Persistent Storage

MySQL data is persisted using named volume: `mysql-data`

## Performance Monitoring

### Actuator Endpoints

Access Spring Boot Actuator endpoints for application monitoring:

```
http://localhost:8080/actuator
http://localhost:8080/actuator/health          # Application health
http://localhost:8080/actuator/metrics         # Application metrics
http://localhost:8080/actuator/info            # Application info
http://localhost:8080/actuator/loggers         # Logger configuration
```

### Health Check Endpoint

```http
GET /actuator/health
```

**Response**
```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "MySQL",
        "hello": 1
      }
    },
    "redis": {
      "status": "UP"
    }
  }
}
```

### Application Info

```http
GET /actuator/info
```

**Response**
```json
{
  "app": {
    "name": "Spring Boot RESTful Web services",
    "description": "Spring Boot RESTful Web services Demo",
    "version": "1.0.0"
  }
}
```

## Error Handling

### Global Exception Handler

The application implements centralized exception handling via `GlobalExceptionalHandler`.

### Common Error Responses

**404 - Resource Not Found**
```json
{
  "message": "User not found with id: 1",
  "details": "URI=/api/users/1",
  "timestamp": "2026-05-11T10:30:00"
}
```

**400 - Bad Request / Validation Error**
```json
{
  "message": "Validation failed",
  "details": "Email already exists",
  "timestamp": "2026-05-11T10:30:00"
}
```

**403 - Forbidden (Unauthorized Access)**
```json
{
  "message": "Access Denied",
  "details": "You do not have permission to access this resource",
  "timestamp": "2026-05-11T10:30:00"
}
```

**401 - Unauthorized (Invalid/Missing JWT Token)**
```json
{
  "message": "Unauthorized",
  "details": "JWT token is invalid or expired",
  "timestamp": "2026-05-11T10:30:00"
}
```

**500 - Internal Server Error**
```json
{
  "message": "Internal Server Error",
  "details": "An unexpected error occurred",
  "timestamp": "2026-05-11T10:30:00"
}
```

### Custom Exceptions

- `ResourceNotFoundException`: Thrown when a user is not found
- `EmailAlreadyExistsException`: Thrown when email already exists in the database

## Contributing

### Getting Started

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit changes: `git commit -m 'Add amazing feature'`
4. Push to branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

### Code Standards

- Follow Google Java Style Guide
- Use meaningful variable and method names
- Add Javadoc comments for public methods
- Write unit tests for new features
- Ensure all tests pass before submitting PR

### Testing

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=UserControllerTest
```

## Troubleshooting

### Common Issues

**MySQL Connection Error**
```
No database selected
```
**Solution**: Ensure MySQL is running and database `user_management` is created.

**Redis Connection Error**
```
Unable to connect to Redis
```
**Solution**: Ensure Redis is running or disable caching by setting `spring.cache.type=none`.

**Port Already in Use**
```
Address already in use: bind
```
**Solution**: Change port in `application.properties` or kill process using port 8080.

**JWT Token Errors**
```
401 Unauthorized: JWT token is invalid or expired
```
**Solution**: 
- Ensure JWT_SECRET is configured correctly in `application.properties`
- Login again to get a fresh token
- Check token expiration time
- Verify Authorization header format: `Authorization: Bearer {token}`

**JWT Secret Not Configured**
```
JWT requires a secret key
```
**Solution**: Set `app.jwt.secret` property in `application.properties` with at least 32 characters

---
## Learning Outcomes

This project helped me understand:

- Spring Boot REST API development
- Layered architecture using Controller, Service, and Repository patterns
- **JWT (JSON Web Token) authentication and authorization**
- **Stateless API security with token-based authentication**
- DTO mapping using MapStruct and ModelMapper
- Exception handling with global exception handlers
- Redis caching integration
- API documentation using Swagger/OpenAPI
- Docker containerization and deployment
- Database integration using Spring Data JPA and Hibernate
- Application monitoring using Spring Boot Actuator
- Spring Security configuration for JWT-based APIs

---

## Author

Sumit Dhandar

- GitHub: https://github.com/sumit-dhandar
- LinkedIn: https://www.linkedin.com/in/sumit-dhandar/
- Email: sumitsdhandar@gmail.com

---

## License

Copyright © 2026 Sumit Dhandar. All rights reserved.

This project is shared for educational and portfolio purposes only.


