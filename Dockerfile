# Этап для сборки приложения с Maven
FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
WORKDIR /srv/app
COPY ./src .
RUN mvn clean package

# Этап для запуска приложения
FROM openjdk:19-jdk-alpine3.16 AS spring
WORKDIR /srv/app/target
COPY --from=build /srv/app/target/marketplace-seller-0.0.1-SNAPSHOT-jar-with-dependencies.jar .
CMD ["java", "-jar", "marketplace-seller-0.0.1-SNAPSHOT-jar-with-dependencies.jar"]
