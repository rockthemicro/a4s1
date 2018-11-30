#!/bin/bash

./gradlew build

docker build -t gs-rest-service .
docker tag gs-rest-service:latest shawney/gs-rest-service:latest
docker push shawney/gs-rest-service:latest
docker rmi shawney/gs-rest-service:latest

docker image ls -a
