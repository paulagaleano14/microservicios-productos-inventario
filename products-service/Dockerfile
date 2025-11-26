FROM eclipse-temurin:17-jdk as build

WORKDIR /app
COPY . .
RUN ./mvnw -q -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/products-service-1.0.0.jar app.jar

EXPOSE 3001
ENTRYPOINT ["java","-jar","app.jar"]
