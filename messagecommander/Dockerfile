FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

COPY ../messagecommander /build

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /build/target/messagecommander-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 15876

CMD ["java", "-jar", "app.jar"]
