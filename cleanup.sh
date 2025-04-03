#!/bin/bash

# InfyGo Airline Booking System Cleanup Script
# This script removes build artifacts and temporary files
#
# How to run this script:
# 1. Make it executable: chmod +x cleanup.sh
# 2. Run it: ./cleanup.sh

echo "=== InfyGo Airline Booking System Cleanup ==="
echo "Cleaning up build artifacts and temporary files..."

# Remove target directory
if [ -d "target" ]; then
    echo "Removing target directory..."
    rm -rf target/
fi

# Remove any log files in the root directory
echo "Removing log files..."
find . -name "*.log" -type f -delete

# Remove any temporary files
echo "Removing temporary files..."
find . -name "*~" -type f -delete
find . -name "*.swp" -type f -delete
find . -name "*.swo" -type f -delete
find . -name "*.tmp" -type f -delete
find . -name "*.bak" -type f -delete

# Remove IDE specific files
echo "Removing IDE specific files..."
find . -name "*.iml" -type f -delete
find . -name ".classpath" -type f -delete
find . -name ".project" -type f -delete

# Remove build artifacts
echo "Removing additional build artifacts..."
find . -name "*.jar" -not -path "*/\.mvn/*" -type f -delete
find . -name "*.war" -type f -delete
find . -name "*.ear" -type f -delete
find . -name "*.class" -type f -delete

# Remove database files
echo "Removing temporary database files..."
find . -name "*.db" -type f -delete
find . -name "*.mv.db" -type f -delete
find . -name "*.trace.db" -type f -delete
find . -name "*.sqlite" -type f -delete

# Remove Node.js related files (if frontend exists)
if [ -f "package.json" ]; then
    echo "Removing Node.js build artifacts..."
    find . -name "node_modules" -type d -exec rm -rf {} +
    find . -name "npm-debug.log" -type f -delete
    find . -name "yarn-error.log" -type f -delete
fi

# Remove OS specific files
echo "Removing OS specific files..."
if [[ "$OSTYPE" == "darwin"* ]]; then
    echo "Removing macOS specific files..."
    find . -name ".DS_Store" -type f -delete
elif [[ "$OSTYPE" == "msys" || "$OSTYPE" == "cygwin" ]]; then
    echo "Removing Windows specific files..."
    find . -name "Thumbs.db" -type f -delete
    find . -name "desktop.ini" -type f -delete
elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
    echo "Removing Linux specific files..."
    find . -name ".directory" -type f -delete
fi

echo "Cleanup complete!"
echo "You may want to rebuild the project with: ./rebuild.sh"
