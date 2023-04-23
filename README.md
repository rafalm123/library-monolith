# Library Monolith (in progress)

Library Monolith is a web application that serves as a digital library for managing users, book releases and borrowing of books.

## Features

- User login and registration system with password encoded in database
- Data protection using DTO Design Pattern
- CRUD operations of searching books and users
- JWT Token Authentication


## Future Development
- Book Borrowing System
- Testing
- Frontend

## Getting Started
1. Clone the repository from GitLab:


    git clone https://gitlab.com/librarians1/library-monolith.git

2. Execute this script from project root directory with command: sh local/startDb.sh on windows use git bash for unix syntax
   or manually console


    docker run --name libraryContainer -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=library -d postgres

3. Access the application:
   Open a web browser and navigate to http://localhost:8080 to access the Library Monolith application.

Technologies Used
- Java 17
- Maven
- GIT
- Docker
- Flyway
- Spring Boot
- Spring Data Jpa
- Spring Security
- PostgreSQL
- Thymeleaf
- JUnit and Mockito