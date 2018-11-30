#!/bin/bash

cd CliClient
./full_build.sh
cd ..

cd MySQLAdmin
./full_build.sh
cd ..

cd gs-rest-service/complete
./full_build.sh
cd ../..
