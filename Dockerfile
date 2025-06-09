FROM eclipse-temurin:22-jdk-alpine
WORKDIR /app
COPY build/libs/User-Service-0.0.2-SNAPSHOT.war User-Service-0.0.2-SNAPSHOT.war
EXPOSE 8080
CMD ["java","-war","User-Service-0.0.2-SNAPSHOT.war"]