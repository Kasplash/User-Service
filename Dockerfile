# Use Java 22 for the build stage
FROM eclipse-temurin:22-jdk-alpine as build
WORKDIR /app

# Copy the Gradle wrapper and build files first (to leverage Docker cache)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Download dependencies (caches this layer)
RUN ./gradlew dependencies --no-daemon

# Copy the rest of the source code
COPY . .

# Build the application without running tests
RUN ./gradlew clean build -x test --no-daemon

# Use Java 22 for the final image
FROM eclipse-temurin:22-jdk-alpine
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
