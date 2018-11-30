FROM openjdk:10.0.2-jdk-oracle
VOLUME /tmp
ARG JAR_FILE=client.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
