# JDockerfile

# Base Image
FROM openjdk:17-jdk-slim

# Set working dir
VOLUME /tmp

# Copies built JAR file into a container
ARG JAR_FILE=target/golfclub-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Entrypoint used to run JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]
