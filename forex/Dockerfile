
FROM maven:latest AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/forex-1.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

