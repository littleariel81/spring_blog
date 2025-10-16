# Spring Blog Project

## Project Overview
This is a blog project built while learning Spring Boot backend development in Java.  
It demonstrates REST API implementation, JWT-based authentication, and CI integration with Gradle and GitHub Actions.

- **Tech Stack:** Java 24, Spring Boot, Spring Data JPA, H2 Database, Gradle, GitHub Actions (CI)
- **CI/CD:** CI (Continuous Integration) configured using GitHub Actions; CD (deployment to AWS) is not implemented yet.
- **Features:**
    - CRUD operations for blog posts
    - JWT token-based authentication
    - CI pipeline runs the tests

---

## Project Structure
spring-blog/
├─ src/main/java # Service, Controller, Domain, Repository
├─ src/main/resources # Configuration, static files
├─ gradle/ # Gradle Wrapper
├─ gradlew / gradlew.bat
├─ build.gradle
└─ .github/workflows/ci.yml