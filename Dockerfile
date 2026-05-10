FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
COPY src ./src

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]