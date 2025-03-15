# Product Management API

This is a RESTful API for managing products in an e-commerce system. It allows you to create, read, update, and delete products.

## Table of Contents
- [Technologies](#technologies)
- [Endpoints](#endpoints)
- [How to Run](#how-to-run)
- [Testing](#testing)
- [Database](#database)

## Technologies
- Java 17
- Spring Boot 3.x
- H2 Database (in-memory)
- Maven
- Lombok

## Endpoints

### List All Products
- **GET** `/products`

### Get Product by ID
- **GET** `/products/{id}`

### Create Product
- **POST** `/products`

### Update Product
- **PUT** `/products/{id}`

### Delete Product
- **DELETE** `/products/{id}`

## How to Run
1. Clone the repository.
2. Navigate to the project directory.
3. Run the application:
 
   mvn spring-boot:run
       Access the API at http://localhost:8080.

## Testing

You can test the API using tools like:

    Postman

    Insomnia

    cURL

Database

The application uses an in-memory H2 database. You can access the H2 console at:

    URL: http://localhost:8080/h2-console

    JDBC URL: jdbc:h2:mem:estoque

    Username: sa

    Password: (leave empty)
