FROM eclipse-temurin:22-jdk-alpine
WORKDIR /app
COPY build/libs/User-Service.war User-Service.war
EXPOSE 8080
CMD ["java","-war","User-Service.war"]