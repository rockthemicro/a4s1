#!/bin/bash

javac src/admin/Main.java 
cd src

jar xf ../lib/mysql-connector-java-8.0.13.jar com
jar cfm admin.jar META-INF/MANIFEST.MF admin/Main.class
jar uf admin.jar com
rm -rf com

mv admin.jar ../
cd ..


docker build -t mysql-admin .
docker tag mysql-admin:latest shawney/mysql-admin:latest
docker push shawney/mysql-admin:latest
docker rmi shawney/mysql-admin:latest

docker image ls -a
