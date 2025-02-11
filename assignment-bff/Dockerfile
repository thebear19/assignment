FROM maven:3.8.5-openjdk-17 AS build

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR bff

COPY --from=build target/*.jar bff.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "bff.jar"]
