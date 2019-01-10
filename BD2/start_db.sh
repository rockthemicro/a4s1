#!/bin/bash

docker run --name oracledb \
--shm-size=1g \
-p 1521:1521 -p 8081:8080 \
-e ORACLE_PWD=caca \
-v /tmp/u01/app/oracle/oradata \
-d \
-v /home/soni/Desktop/Facultate/anul4/sem1/BD2/oracle_scripts:/u01/app/oracle/scripts/startup \
oracle/database:11.2.0.2-xe
