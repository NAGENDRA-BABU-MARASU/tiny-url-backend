#FROM openjdk:17
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/target/TinyURL-0.0.1-SNAPSHOT.jar"]


