#!/bin/bash

cd AdminDB
mvn package
docker rmi admininterface
docker build -t admininterface .

cd ../TextClient
mvn package
docker rmi textclient
docker build -t textclient .

cd ../Server/server
./gradlew build
docker rmi restserver
docker build -t restserver .
