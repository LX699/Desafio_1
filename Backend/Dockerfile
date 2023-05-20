FROM maven:3.8.5-openjdk-17-slim AS builder

WORKDIR /build

COPY . .

RUN mvn clean package

FROM openjdk:17-slim

EXPOSE 8080

COPY "./target/desafio1-0.0.1-SNAPSHOT.jar" "apicar.jar"

CMD ["java", "-jar", "apicar.jar"]