FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Agregar permisos de ejecución al script mvnw
RUN chmod +x ./mvnw
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]