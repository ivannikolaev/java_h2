FROM eclipse-temurin:21-jdk-jammy

COPY target/*.jar app.jar
EXPOSE 8080
ENV db_url="jdbc:postgresql://127.0.0.1:5432/students"

ENTRYPOINT ["java", "-jar", "/app.jar"]