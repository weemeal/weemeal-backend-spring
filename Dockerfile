# Start with a base image containing Java runtime
FROM amazoncorretto:21-alpine-jdk

# Create a directory
WORKDIR /app

# Copy all the files from the current directory to the image
COPY . .

# build the project avoiding tests
RUN ./gradlew clean build -x test

# Expose port 8080
EXPOSE 8081

# Run the jar file
CMD ["java", "-jar", "./build/libs/weemeal-backend-spring-0.0.1-SNAPSHOT.jar"]