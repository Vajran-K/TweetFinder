FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# DEBUG: show exactly what jar exists
RUN echo "==== JARS IN TARGET ====" && ls -lh target

EXPOSE 8080

# Run whatever jar exists (fat jar guaranteed)
CMD ["sh", "-c", "java -jar target/*.jar"]
