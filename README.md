# Task Tracker API

Task Tracker is a RESTful API built with Spring Boot to manage and track tasks. This application allows users to add, update, delete, and list tasks while categorizing them as "todo," "in-progress," or "done." The data is stored in a PostgreSQL database.

---

## Features

- Add, update, and delete tasks
- Mark tasks as "in-progress" or "done"
- List all tasks or filter by status:
    - All tasks
    - Done tasks
    - Not done tasks
    - In-progress tasks
- Store tasks in a PostgreSQL database

---

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- ModelMapper
- JUnit 5 & Mockito (for testing)

---

## Prerequisites

Before you begin, ensure you have the following installed on your system:
- Java 21
- Maven
- PostgreSQL

---

## Getting Started

### 1. Clone the Repository

```sh
git clone https://github.com/bdkamaci/task-tracker.git
cd task-tracker
 ```

### 2. Set Up PostgreSQL Database

- Create a new database in PostgreSQL:

```sh
CREATE DATABASE todo_db;
 ```

- Update application.properties file with your database credentials:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 3. Build and Run the Application
```sh
# Using Maven
./mvnw clean install
./mvnw spring-boot:run
```

## API Endpoints

### Task Endpoints

1. Add a Task:
```
POST /api/v1/todos
```
  - Request Body:
      ```json
      {
      "description" : "Buy groceries"
      }
      ```
  - Response:
      ```json
      {
      "id": 1, 
      "description" : "Buy groceries", 
      "status" : "todo", 
      "createdAt" : "2025-02-17T10:00:00", 
      "updatedAt" : "2025-02-17T10:00:00"
      }
      ```
2. Update a Task:
```
PUT /api/v1/todos/{id}
```

3. Delete a Task:
```
DELETE /api/v1/todos/{id}
```
4. Mark Task as In Progress:
```
PATCH /api/v1/todos/{id}/in-progress
```

5. Mark Task as Done:
```
PATCH /api/v1/todos/{id}/done
```
6. List All Tasks:
```
GET /api/v1/todos
```
- List Tasks by Status:
```
GET /api/v1/todos?status=done
GET /api/v1/todos?status=todo
GET /api/v1/todos?status=in-progress
```

## Project Structure

```css
src
├── main
│   ├── java
│   │   └── com.bdkamaci.tasktracker
│   │       ├── controller
│   │       │   └── ToDoController.java
│   │       ├── dto
│   │       │   ├── ToDoRequest.java
│   │       │   └── ToDoResponse.java
│   │       ├── model
│   │       │   └── ToDo.java
│   │       ├── repository
│   │       │   └── ToDoRepository.java
│   │       └── service
│   │           └── ToDoService.java
│   └── resources
│       └── application.properties
└── test
    └── java
        └── com.bdkamaci.tasktracker
            ├── controller
            │   └── ToDoControllerTest.java
            └── service
                └── ToDoServiceTest.java
```

## Testing
JUnit 5 and Mockito are used for unit and integration tests.
- Run tests using:
```sh
./mvnw test
```

## Installation and Usage
1. Clone the repository and navigate to the project directory.
2. Configure PostgreSQL settings in application.properties.
3. Build and run the application using Maven.
4. Access the API endpoints using a tool like Postman or curl.

## Contribution
Feel free to fork the project and create pull requests. Contributions are always welcome!

## License
This project is licensed under the MIT License. See the LICENSE file for more details.

## Contact
Created by Burcu Doga KAMACI - feel free to contact me!

## Acknowledgments
- Spring Boot Documentation
- PostgreSQL Documentation
- ModelMapper Library