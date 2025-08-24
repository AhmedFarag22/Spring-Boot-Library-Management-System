# Library Management REST API

A clean, production‑ready Spring Boot 3 REST API for a small library system. It covers the full CRUD lifecycle for **Books** and **Patrons**, and models the lending workflow via **Borrowing Records** (borrow / return) with transactional guarantees.

This project is deliberately minimal yet professional: layered architecture, clear endpoints, PostgreSQL persistence, exception handling, and integration tests. Perfect for demonstrating backend fundamentals to recruiters & hiring managers.

---

## ✨ Highlights

* **Domain model**: `Book`, `Patron`, `BorrowingRecord` with proper relations.
* **RESTful endpoints**: CRUD for Books & Patrons, plus `/borrow` & `/return` flows.
* **Transactional business logic**: borrowing/returning wrapped in service-level transactions.
* **Global error handling**: consistent error responses via `@ControllerAdvice`.
* **JPA/Hibernate + PostgreSQL**: production-grade persistence.
* **Tests**: repository/service layer scenarios using `@SpringBootTest`.
* **Clean code**: separation of concerns (Controller → Service → Repository).

> Stack: **Java 21**, **Spring Boot 3**, **Spring Web**, **Spring Data JPA**, **Jakarta Persistence**, **PostgreSQL**.

---

## 🧱 Domain & Relationships

```
Patron (1)            BorrowingRecord (many)            Book (1)
   │                         ▲     ▲                       │
   └─────────────────────────┘     └───────────────────────┘

BorrowingRecord
- id
- book_id  → ManyToOne Book
- patron_id→ ManyToOne Patron
- borrowDate
- returnDate (null until returned)
```

### Entities

* **Book**: `id`, `title`, `author`, `publicationYear`, `isbn`
* **Patron**: `id`, `name`, `contactInfo`
* **BorrowingRecord**: link between a `Book` and a `Patron` with dates

---

## 🗺️ API Overview

Base URL: `http://localhost:8080`

### Books

* `GET /api/books` → list all
* `GET /api/books/{id}` → get by id
* `POST /api/books` → create
* `PUT /api/books/{id}` → update
* `DELETE /api/books/{id}` → delete

### Patrons

* `GET /api/patrons` → list all
* `GET /api/patrons/{id}` → get by id
* `POST /api/patrons` → create
* `PUT /api/patrons/{id}` → update

### Borrow / Return

* `POST /api/borrow/{bookId}/patron/{patronId}` → create a BorrowingRecord with `borrowDate=now`
* `PUT  /api/return/{bookId}/patron/{patronId}` → sets `returnDate=now` for the active record

---

## 📦 Request/Response Examples

### Create a Book

```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
        "title":"Clean Code",
        "author":"Robert C. Martin",
        "publicationYear":2008,
        "isbn":"9780132350884"
      }'
```

### Create a Patron

```bash
curl -X POST http://localhost:8080/api/patrons \
  -H "Content-Type: application/json" \
  -d '{
        "name":"Ahmed Farag",
        "contactInfo":"ahmed@example.com"
      }'
```

### Borrow a Book

```bash
curl -X POST http://localhost:8080/api/borrow/1/patron/1
```

### Return a Book

```bash
curl -X PUT http://localhost:8080/api/return/1/patron/1
```

---

## 🚀 Getting Started

### Prerequisites

* **Java 21**
* **Maven 3.9+**
* **PostgreSQL 14+**

### Database Setup

Create a database named `library` and a user with access (example below):

```sql
CREATE DATABASE library;
CREATE USER postgres WITH ENCRYPTED PASSWORD 'A7med';
GRANT ALL PRIVILEGES ON DATABASE library TO postgres;
```

### Application Configuration

`src/main/resources/application.properties` (example)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library
spring.datasource.username=postgres
spring.datasource.password=A7med

# Hibernate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Schema management (choose one strategy)
# spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.hibernate.ddl-auto=update

# Error messages
server.error.include-message=always
```

> Tip: Use **one** `spring.jpa.hibernate.ddl-auto` property. `update` is convenient locally; migrations (Flyway/Liquibase) are recommended for production.

### Run the App

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## 🧪 Testing

Run all tests:

```bash
mvn test
```

The suite includes integration-style tests for the Book and Patron flows using Spring Boot’s test support and the real JPA repository.

---

## 🧰 Project Structure

```
com.example.demo.Library
├── Book.java                     # Entity
├── BookRepository.java           # JPA repository
├── BookService.java              # Business logic
├── BookController.java           # REST endpoints
├── Patron.java
├── PatronRepository.java
├── PatronService.java
├── PatronController.java
├── BorrowingRecord.java
├── BorrowingRecordRepository.java
├── BorrowingRecordService.java
├── BorrowingRecordController.java
└── GlobalExceptionHandler.java   # @ControllerAdvice
```

---

## 🛡️ Error Handling

Centralized via `GlobalExceptionHandler` with `@ControllerAdvice`, returning meaningful messages and appropriate HTTP status codes for unexpected errors.

---


## 📖 API Documentation (Optional)

Add OpenAPI via **springdoc-openapi** to auto-generate Swagger UI:

```xml
<!-- pom.xml -->
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.5.0</version>
</dependency>
```

Then visit: `http://localhost:8080/swagger-ui.html`

---


## 🧭 Roadmap

* Pagination & filtering on books/patrons
* Search by title/author/ISBN
* Prevent double-borrowing of the same copy
* Overdue tracking & fines
* Soft deletes & audit fields (createdAt/updatedAt)
* Role-based security with Spring Security + JWT

---

## 🙌 How to Run a Quick Demo

1. Start PostgreSQL and the app.
2. Create a patron and a book (see cURL above).
3. Borrow the book, list records, return it.
4. Watch `borrowDate`/`returnDate` update as expected.

---

## 💼 Why This Project Stands Out

* Demonstrates **end-to-end backend craftsmanship** with Spring Boot 3.
* Shows understanding of **transactions, persistence, and API design**.
* Easy to extend into a complete, production-grade service.

---

## 👤 Author

**Ahmed Farag** — Full‑Stack Developer (Spring Boot & Angular)

* LinkedIn: `ahmed-farag-93a74324a`

> شكراً! تم بناء هذا المشروع ليكون بسيط وواضح ويعكس مستوى هندسي احترافي.
