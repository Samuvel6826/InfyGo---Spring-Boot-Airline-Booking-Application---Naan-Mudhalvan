#!/bin/bash

# InfyGo Airline Booking System Run Script
# This script runs the application
#
# How to run this script:
# 1. Make it executable: chmod +x run.sh
# 2. Run it: ./run.sh

echo "=== InfyGo Airline Booking System Run ==="
echo "Starting the application..."

# Run the application using Maven wrapper
./mvnw spring-boot:run

echo "Application stopped."
