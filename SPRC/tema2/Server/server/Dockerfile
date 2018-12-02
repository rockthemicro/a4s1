FROM openjdk:10.0.2-jdk-oracle
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/server-0.0.1.jar
ADD ${JAR_FILE} entry.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/entry.jar"]
