
FROM maven:latest AS build
COPY currency_exchange/src /home/app/src
COPY currency_exchange/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/currencyexchange-1.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

