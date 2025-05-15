````markdown
# 🏥 PHC Attendance System - Spring Boot Backend

This is the backend API for the PHC Attendance System, built with **Spring Boot** and connected to a **MySQL** database. It supports user management, attendance tracking, and email notifications.

---

## 📦 Prerequisites

- **Java 17** or higher
- **Maven**
- **MySQL** installed and running
- Optional: `.env` support tool (like IntelliJ EnvFile plugin or manual export in shell)

---

## ⚙️ Environment Variables

Before running the application, ensure the following environment variables are set:

| Variable Name        | Description                       | Example                           |
|----------------------|-----------------------------------|-----------------------------------|
| `app_password`       | Application password              | `heaklfjklamk`                    |
| `db_password`        | MySQL database password           | `6557`                            |
| `db_url`             | JDBC URL for database connection  | `jdbc:mysql://localhost:3306/phc_database` |
| `db_username`        | MySQL username                    | `root`                            |
| `expiration_time`    | JWT expiration in milliseconds    | `360000`                          |
| `from_mail`          | Sender email for notifications    | `email@gmail.com`                 |
| `jwt_secret_key`     | Secret key for JWT tokens         | `secret-key`                      |

---

### ✅ How to Set Environment Variables

#### Option 1: Export in Terminal (for local run)
```bash
export app_password=heaklfjklamk
export db_password=6557
export db_url=jdbc:mysql://localhost:3306/phc_database
export db_username=root
export expiration_time=360000
export from_mail=email@gmail.com
export jwt_secret_key=secret-key
````

#### Option 2: Create a `.env` file (if using a plugin/tool that supports it)

```
app_password=heaklfjklamk
db_password=6557
db_url=jdbc:mysql://localhost:3306/phc_database
db_username=root
expiration_time=360000
from_mail=email@gmail.com
jwt_secret_key=secret-key
```

---

## 🚀 Run the Application

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/phc-attendance-backend.git
cd phc-attendance-backend
```

### 2. Build the Project

```bash
mvn clean install
```

### 3. Run the Spring Boot App

```bash
mvn spring-boot:run
```

OR if you’ve built the `.jar` file:

```bash
java -jar target/phc-attendance-system-0.0.1-SNAPSHOT.jar
```

---

## 🧪 API Testing

* Base URL: `http://localhost:8080`
* You can test the API using **Postman**, **Insomnia**, or **cURL**
* Swagger/OpenAPI docs: `http://localhost:8080/swagger-ui.html` *(if configured)*

---

## 🛠 Tech Stack

* **Spring Boot**
* **MySQL**
* **JPA / Hibernate**
* **Lombok**
* **JWT for authentication**
* **JavaMailSender for email**

---

