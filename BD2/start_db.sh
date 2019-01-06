#!/bin/bash

docker run --name oracledb \
--shm-size=1g \
-p 1521:1521 -p 8081:8080 \
-e ORACLE_PWD=caca \
-v /tmp/u01/app/oracle/oradata \
oracle/database:11.2.0.2-xe
