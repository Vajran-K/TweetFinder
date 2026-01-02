# Use official Java 17 image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the project
RUN chmod +x mvnw && ./mvnw clean package

# Expose Render port
EXPOSE 8080

# Run the application
CMD sh -c "java -jar target/*.jar"

