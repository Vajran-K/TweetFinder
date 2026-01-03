FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy everything
COPY . .

# Build fat jar (includes dependencies)
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Render sets PORT automatically
EXPOSE 8080

# Run the shaded (fat) jar
CMD ["java", "-jar", "target/demo-1.0-SNAPSHOT-shaded.jar"]

