FROM openjdk:10.0.2-jdk-oracle
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/gs-rest-service-0.1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
