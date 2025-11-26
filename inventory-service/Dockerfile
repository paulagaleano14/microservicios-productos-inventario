FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -q -DskipTests package

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/inventory-service-1.0.0.jar app.jar
EXPOSE 3002
ENTRYPOINT ["java","-jar","app.jar"]
