
FROM maven:latest AS build
COPY WEB_currency_exchange/src /home/app/src
COPY WEB_currency_exchange/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/webexchange-1.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

