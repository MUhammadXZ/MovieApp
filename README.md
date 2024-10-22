# Movie Management Application

## Overview
This is a full-stack Movie Management application built with Angular for the frontend and Spring Boot for the backend. It allows users to view and manage movies.

## Technologies Used
- Angular 16+
- Spring Boot
- PostgreSQL

## Setup Instructions

### Backend Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/MUhammadXZ/MovieApp.git
   cd movie-management-backend
### Configure PostgreSQL
1. Create a database named `movie_management`.
2. Update the `application.properties` file with your database credentials.

### Run Liquibase migrations
```bash
mvn liquibase:update
