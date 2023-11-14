# Use the official OpenJDK base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY build/libs/FeedApp-0.0.1-SNAPSHOT.jar /app
COPY .env /app

# Expose the port that your Spring Boot application runs on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "FeedApp-0.0.1-SNAPSHOT.jar"]

