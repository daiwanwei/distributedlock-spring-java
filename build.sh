#!/bin/bash
sed -i  's/active: dev/active: pro/g' src/main/resources/application.yml
docker build -t distributedlock-spring-java:latest .

