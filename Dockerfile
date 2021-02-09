FROM maven:3.6.3-openjdk-15-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:15
COPY --from=build /home/app/target/telegramMailBot-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/local/lib/bot.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/bot.jar"]
