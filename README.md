# User Service (WIP)

A cloud-native user management API built with Spring Boot and deployed on AWS. This project demonstrates Java, Spring, containerization, and AWS architecture for a production-like service.

## Features
- User Basic AUTH
- CRUD for Users
- Dockerized app 
- CI/CD to build and deploy to AWS (ECR + EKS)

## Architecture
- Spring Boot REST API (Java 21)
- PostgreSQL DB (Currently in memory H2 DB)
- ECR + EKS for container deployment
- GitHub Actions for CI/CD

## Local development
Prerequisites:
- Java 21, Maven
- Docker
- Postgres or Testcontainers

Run locally:
2. Build and run with Gradle:
    - ./gradlew clean build
    - java -jar build/libs/user-service-0.0.1-SNAPSHOT.jar
3. Alternatively, run with Docker:
    - docker build -t user-service .
    - docker run --rm -p 8080:8080 user-service

## Tests
- Unit tests: ./gradlew test

## Deployment
- GitHub Actions builds Docker image and pushes to Amazon ECR and deploys to EKS

## Roadmap / Extensions
- Add metrics and health endpoints for prometheus


## How to contribute
- Fork, create a branch feature/<name>, open a PR.
- Follow coding standards and add tests for new features


