# BookVerse 📚 – Microservice-Based Book Platform

BookVerse is a microservice-based book management platform designed using **Java (Servlets + JDBC)** and **Node.js (Express)**. This project demonstrates a clean separation of concerns between the **Book Service** and **User Service**, connected via HTTP communication.

---

## 🧱 Architecture

+------------------+ +---------------------+
| User Service | <---> | Book Service |
| (Node.js) | | (Java Servlet API) |
+------------------+ +---------------------+


- **User Service (Node.js + Express):**  
  Handles user registration, authentication, and user-related logic.

- **Book Service (Java + JDBC + Tomcat):**  
  Responsible for managing books (CRUD), search, and book listing.

Both services communicate via **HTTP APIs** and can be independently scaled or deployed.

---

## 🛠️ Tech Stack

### 🔹 User Service (Node.js)
- Node.js + Express
- MySQL
- JWT Authentication
- REST API

### 🔸 Book Service (Java)
- Java Servlet API
- JDBC for MySQL connection
- Apache Tomcat Server
- REST-style routing

---

## 🚀 Features

- 📚 Add, edit, delete, and view books
- 🔐 Register and authenticate users
- 🔍 Search books by title or author
- ⚙️ Microservice architecture with independent deployment

---

## 📁 Folder Structure

### User Service (Node.js)
user-service/
├── controllers/
├── routes/
├── models/
├── middlewares/
├── utils/
└── index.js


### Book Service (Java)
book-service/
├── bin/
├── build/
├── gradle/
├── grpc-protos/
| └── book.proto
├── build
├── gradle
├── src/
│ └── main/
│   ├── java 
│   ├── proto 
│   ├── resources
│   └── webapp
└── webapp/
└── WEB-INF/web.xml


---

## 🔧 Setup Instructions

### 🟦 Book Service (Java)

1. Open the project in **Eclipse** or **IntelliJ**.
2. Add **Tomcat server** and configure MySQL JDBC driver.
3. Setup your database:

```sql
CREATE DATABASE bookverse;
-- Run relevant table creation scripts


Update DB config in DBUtil.java.
Deploy using Tomcat and access:
http://localhost:8080/book-service/books

🟩 User Service (Node.js)
# Clone the repository
cd user-service

# Install dependencies
npm install

# Add environment variables
touch .env
# DB_HOST=localhost
# DB_USER=root
# DB_PASS=yourpassword
# DB_NAME=bookverse_users
# JWT_SECRET=your_jwt_secret

# Run the server
npm start

🔗 API Endpoints
User Service
POST /register – Register new user

POST /login – Authenticate user

GET /users/:id – Get user details

Book Service
GET /books – List all books

POST /books – Add new book

PUT /books/:id – Update book info

DELETE /books/:id – Remove book

🎯 Future Improvements
Add gRPC or REST gateway for service discovery

Implement Dockerization for deployment

Add caching (Redis) and logging (Winston)

Deploy both services on Kubernetes or separate containers

👨‍💻 Author
Aquib Khan
📧 aquibk580@gmail.com
🔗 GitHub Profile![Screenshot 2025-07-01 182822](https://github.com/user-attachments/assets/54e34b58-f505-47be-b0f3-f336ddc55cf3)
