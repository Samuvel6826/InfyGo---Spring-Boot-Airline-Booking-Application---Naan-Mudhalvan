#!/bin/bash

# Script to cleanup unnecessary components in the project

echo "Cleaning up project structure..."

# Remove empty directories in resources
rm -rf src/main/resources/static
rm -rf src/main/resources/templates

# Remove unnecessary files
rm -f HELP.md
rm -f rebuild.sh

# Remove target folder (will be regenerated during build)
rm -rf target/

echo "Cleanup completed!"
