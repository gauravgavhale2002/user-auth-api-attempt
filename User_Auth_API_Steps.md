# 🛡️ User Registration & Authentication API (Spring Boot + MySQL)

**Last Updated**: 2025-06-29 16:10:52

---

## 📁 Project Overview

A Spring Boot application that supports:
- User Registration
- Login with password retry lockout logic
- Forgot Password (with secret questions)

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Security (PasswordEncoder)
- JPA + Hibernate
- MySQL
- Postman

---

## 🔗 API Endpoints

### 1️⃣ Register User

- **Method**: `POST`
- **URL**: `http://localhost:8080/api/auth/register`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "email": "gaurav123@gmail.com",
  "password": "Gaurav@123",
  "confirmPassword": "Gaurav@123",
  "secretAnswer1": "Blue",
  "secretAnswer2": "Dog",
  "secretAnswer3": "Sunrise",
  "secretAnswer4": "Rahul"
}
```

---

### 2️⃣ Login

- **Method**: `POST`
- **URL**: `http://localhost:8080/api/auth/login`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "email": "gaurav123@gmail.com",
  "password": "Gaurav@123"
}
```

---

### 3️⃣ Reset Password (via Secret Questions)

- **Method**: `POST`
- **URL**: `http://localhost:8080/api/auth/reset-password`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "email": "gaurav123@gmail.com",
  "secretAnswer1": "Blue",
  "secretAnswer2": "Dog",
  "secretAnswer3": "Sunrise",
  "secretAnswer4": "Rahul",
  "newPassword": "New@1234",
  "confirmPassword": "New@1234"
}
```

---

## ⚙️ application.properties

```properties
spring.application.name=user-auth-api

# ============================
# 🔗 Database Configuration
# ============================
spring.datasource.url=jdbc:mysql://localhost:3306/userdb_otp
spring.datasource.username=root
spring.datasource.password=Gaurav@9421
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# ============================
# 🔐 Logging & Server
# ============================
logging.level.root=INFO
logging.level.com.example.auth.service=DEBUG
server.port=8080
```

---

## 📦 Postman Collection

1. Open Postman.
2. Create a new collection: `User Auth API`
3. Add 3 requests (Register, Login, Reset Password) using the details above.
4. Export Collection → Collection v2.1 → Save as `user-auth-api.postman_collection.json`

---

## 📌 Notes

- Password and Secret Answers are stored using **BCryptPasswordEncoder**
- Lockout logic after **3 failed attempts** (30 seconds lock)
- Reset password verifies all 4 secret answers

