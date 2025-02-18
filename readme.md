# E-Commerce Application

This is a demo project for an e-commerce application built using Spring Boot. The application provides functionalities for managing users, products, orders, and stores.

## Table of Contents

- Features
- Technologies Used
- Getting Started
- API Endpoints
- Configuration
- License

## Features

- User management (Admin, Customer, Store Owner)
- Product management
- Order management
- Store management
- JWT-based authentication

## Technologies Used

- Java 21
- Spring Boot 3.4.2
- Spring Data JPA
- PostgreSQL
- Lombok
- Auth0
- Maven
- Swagger

## Getting Started

### Prerequisites

- Java 21
- Maven
- PostgreSQL

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/e-commerce.git
    cd e-commerce
    ```

2. Configure the database in application.properties:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
    spring.datasource.username=your_user_name
    spring.datasource.password=your_password
    ```

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```
5. Open your favorite web browser and paste it
   ```
   http://localhost:8080/swagger-ui/index.html
   ```
## API Endpoints

### User Endpoints

- **Create Customer**: `POST /users/create-customer`
- **Create Admin**: `POST /users/create-admin`
- **Create Store Owner**: `POST /users/create-store-owner`
- **Update Customer**: `PUT /users/update-customer`
- **Update Admin**: `PUT /users/update-admin`
- **Login**: `POST /users/login`
- **Delete Admin**: `DELETE /users/delete-admin`

### Store Endpoints

- **Create Store**: `POST /stores/create-store`
- **Add Product**: `POST /stores/add-product`
- **Update Product Name**: `PUT /stores/update-product-name`
- **Update Product Price**: `PUT /stores/update-product-price`
- **Update Product Stock**: `PUT /stores/update-product-stock`
- **Create Store For Admin**: `POST /stores/create-store-for-admin`
- **Delete Store For Admin**: `DELETE /stores/delete-store-for-admin`

### Order Endpoints

- **Create Order**: `POST /orders/create-order`
- **Approve Order**: `PUT /orders/approve-order`
- **Get Total Sales for Today**: `GET /orders/get-total-sales-for-today-for-admin`
- **Get Total Sales for All Stores for Today**: `GET /orders/get-total-sales-for-all-stores-for-today-for-admin`
- **Get Monthly Sales Info for Admin**: `GET /orders/get-store-monthly-sales-info`

## Configuration

The application configuration is managed in the application.properties file located in resources. Key configurations include:

```properties
spring.application.name=e-commerce
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_user_name
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true

jwt.secret=your_jwt_secret_key
jwt.duration=86400000
```

## License

This project is licensed under the MIT License. See the LICENSE file for details.
