#!/bin/bash
echo "Building the sofka-lab-commons project"
cd sofka-lab-common
mvn clean install -DskipTests
cd ..

# Build the project
echo "Building the sofka-lab-accounts-service project"
cd sofka-lab-accounts-service
mvn clean package -DskipTests
cd ..

echo "Building the sofka-lab-api-gateway project"
cd sofka-lab-apigateway-server
mvn clean package -DskipTests
cd ..

echo "Building the sofka-lab-customer-service project"
cd sofka-lab-customers-service
mvn clean package -DskipTests
cd ..

echo "Building the sofka-lab-eureka-server project"
cd sofka-lab-eureka-server
mvn clean package -DskipTests