#!/bin/bash

# Simple execution script for the airline booking application
echo "Starting InfyGo Airline Booking Application..."

# Perform build if requested
if [ "$1" == "--build" ]; then
    echo "Building application..."
    mvn clean package -DskipTests
fi

# Run the application
mvn spring-boot:run
