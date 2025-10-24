# ============================
# 1. Build stage
# ============================
FROM gradle:8.7-jdk17 AS builder

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and build files first (for better caching)
COPY ../../User-Service-main/gradlew ./
COPY ../../User-Service-main/gradle ./gradle
COPY ../../User-Service-main/build.gradle settings.gradle ./

# Download dependencies (cached)
RUN ./gradlew dependencies --no-daemon

# Copy the rest of the source code
COPY ../../User-Service-main/src ./src

# Build the application (creates a fat JAR under build/libs)
RUN ./gradlew bootJar --no-daemon

# ============================
# 2. Runtime stage
# ============================
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
