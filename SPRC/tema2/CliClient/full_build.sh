#!/bin/bash

javac src/client/Main.java 
cd src

jar cfm client.jar META-INF/MANIFEST.MF client/Main.class

mv client.jar ../
cd ..


docker build -t client .
docker tag client:latest shawney/client:latest
docker push shawney/client:latest
docker rmi shawney/client:latest

docker image ls -a
