FROM eclipse-temurin:17-jdk-alpine as build

# Set the working directory inside the container
WORKDIR /app
COPY . .

# Build the application
RUN ./gradlew build --no-daemon -x test

# === Production Stage ===
FROM eclipse-temurin:17-jdk-alpine as production

# Set the working directory inside the container
WORKDIR /app
COPY --from=build /app/build/libs/profile_ms-0.0.1-SNAPSHOT.jar .

# Expose the port your Spring Boot application will run on
EXPOSE 8084
CMD ["java", "-jar", "profile_ms-0.0.1-SNAPSHOT.jar"]

