# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/WMS-0.0.1-SNAPSHOT.jar
ARG CONFIG_FILE=src/main/resources/application-prod.properties

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar
COPY ${CONFIG_FILE} application-prod.properties

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","app.jar","-spring.config.location","file:///opt/app/application-prod.properties"]