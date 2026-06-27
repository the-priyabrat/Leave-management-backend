# Leave Management Backend

A robust **Leave Management System Backend** built with **Java, Spring Boot, MySQL, Keycloak, and REST APIs**. The application provides secure authentication and role-based authorization, enabling employees to request leave, managers to approve or reject requests, and users to track their leave balances and request history seamlessly.

## 🚀 Features

* 🔐 Secure authentication and authorization using **Keycloak** and **JWT**.
* 👤 User registration and login.
* 🛡️ Role-Based Access Control (RBAC) for Employees, Reporting Managers, and Administrators.
* 📝 Employees can submit leave requests.
* ✅ Reporting Managers can approve or reject leave requests assigned to their team.
* 📊 Track leave balance and leave history.
* 📅 View pending, approved, and rejected leave requests.
* 🔄 RESTful APIs for frontend integration.
* 💾 Persistent data storage using MySQL.
* ⚡ Scalable and modular Spring Boot architecture.

---

## 🛠️ Tech Stack

* **Java 17+**
* **Spring Boot**
* **Spring Security**
* **Keycloak**
* **JWT Authentication**
* **MySQL**
* **Spring Data JPA (Hibernate)**
* **REST API**
* **Maven**

---

## 📂 Project Structure

```text
leave-management-backend/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── entity/
│   │   │   ├── dto/
│   │   │   ├── security/
│   │   │   ├── config/
│   │   │   └── exception/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│── pom.xml
└── README.md
```

---

## 🔑 Authentication

Authentication is secured using **Keycloak** and **JWT**.

Supported authentication features include:

* User Registration
* User Login
* JWT Token Generation
* Token Validation
* Role-Based Authorization
* Protected REST Endpoints

---

## 👥 User Roles

### Employee

* Register/Login
* Apply for Leave
* View Leave Requests
* Track Leave Balance
* View Leave History

### Reporting Manager

* View Team Leave Requests
* Approve Leave
* Reject Leave
* View Employee Leave Balance

### Administrator

* Manage Users
* Assign Reporting Managers
* Configure Roles
* Monitor Leave Records

---

## 📡 REST API Modules

### Authentication

* Register User
* Login User
* Refresh Token

### User

* Get Profile
* Update Profile
* View Leave Balance

### Leave

* Apply Leave
* Cancel Leave
* View Leave History
* View Pending Requests

### Manager

* Get Assigned Leave Requests
* Approve Leave Request
* Reject Leave Request

---

## 💾 Database

The application uses **MySQL** to store:

* Users
* Roles
* Leave Requests
* Leave Balance
* Reporting Manager Mapping
* Authentication Details

---

## ⚙️ Getting Started

### Clone the Repository

```bash
git clone https://github.com/your-username/leave-management-backend.git
```

### Navigate to the Project

```bash
cd leave-management-backend
```

### Configure MySQL

Update the database credentials in:

```properties
application.properties
```

### Configure Keycloak

Set the following properties:

* Keycloak Server URL
* Realm
* Client ID
* Client Secret

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The backend will start on:

```text
http://localhost:8080
```

---

## 🎯 Project Workflow

1. User registers or logs in securely.
2. JWT token is issued after successful authentication.
3. Employee submits a leave request.
4. The request is automatically routed to the employee's assigned Reporting Manager.
5. Reporting Manager reviews and approves/rejects the request.
6. Leave balance is updated automatically after approval.
7. Employees can monitor their leave history, balances, and request status in real time.

---

## 🌟 Future Enhancements

* Email Notifications
* Leave Calendar Integration
* Multi-Level Approval Workflow
* Holiday Management
* Department Management
* Audit Logs
* Docker Deployment
* CI/CD Pipeline
* Swagger/OpenAPI Documentation
* Unit & Integration Testing

---

## 🤝 Contributing

Contributions are welcome! Feel free to fork the repository, create a feature branch, and submit a pull request.

---

## 📄 License

This project is licensed under the **MIT License**.
