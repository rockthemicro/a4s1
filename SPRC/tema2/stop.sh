#!/bin/bash

docker stack rm system
docker swarm leave --force
