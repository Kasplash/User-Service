FROM eclipse-temurin:22-jdk-alpine
WORKDIR /app
COPY build/libs/User-Service-0.0.1-SNAPSHOT.war user-service.war
EXPOSE 8080
CMD ["java","-war","user-service.war"]