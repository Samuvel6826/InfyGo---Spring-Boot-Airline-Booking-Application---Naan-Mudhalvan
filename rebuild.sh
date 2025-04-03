#!/bin/bash

# InfyGo Airline Booking System Rebuild Script
# This script cleans and rebuilds the application
#
# How to run this script:
# 1. Make it executable: chmod +x rebuild.sh
# 2. Run it: ./rebuild.sh

echo "=== InfyGo Airline Booking System Rebuild ==="
echo "Cleaning and rebuilding the application..."

# Clean and compile
./mvnw clean compile

# Package the application
echo "Creating deployable package..."
./mvnw package -DskipTests

echo "Rebuild complete!"
echo "You can now run the application with: ./run.sh"
