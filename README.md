# InfyGo Airline Booking System

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen.svg)](https://spring.io/projects/spring-boot)

A lightweight, efficient airline booking system designed for performance and simplicity. This application demonstrates best practices in Java application architecture and design patterns.

## Table of Contents

- [Architecture](#architecture)
- [Performance Optimizations](#performance-optimizations)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Features](#features)
  - [Add Flight](#1-add-flight)
  - [Search Flights](#2-search-flights)
  - [Display All Flights](#3-display-all-flights)
  - [Data Validation](#4-data-validation)
- [Technical Highlights](#technical-highlights)
- [Developer Notes](#developer-notes)
- [Screenshots](#screenshots)
- [Troubleshooting](#troubleshooting)

## Architecture

The application follows a layered architecture pattern:

1. **Presentation Layer (Main.java)**: CLI interface for user interaction
2. **Service Layer**: Business logic and validation
3. **Repository Layer**: In-memory data storage with optimized indexing
4. **Domain Layer**: Entity definitions
5. **Cross-cutting Concerns**: Logging using AOP

![Architecture Diagram](https://via.placeholder.com/600x400?text=InfyGo+Architecture+Diagram)

## Performance Optimizations

- Uses HashMap for O(1) flight lookup by ID
- Implements route-based indexing for faster flight searches
- Lazy initialization for Spring components
- Efficient data structures throughout the codebase
- Optimized logging configuration for production environments

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Git (optional, for cloning the repository)

### Installation

1. Clone the repository:

   ```bash
   git clone https://your-repository-url/infygo-airline-booking.git
   cd airline-booking
   ```

2. Build the project:
   ```bash
   ./run.sh --build
   ```

### Running the Application

Execute the run script to start the application:

```bash
./run.sh
```

## Features

### 1. Add Flight

Add a new flight with details like airline, source, destination, fare, journey date, and seat count.

- Automatic flight ID generation
- Full validation of all input parameters
- Real-time confirmation of successful additions

### 2. Search Flights

Search for flights based on source, destination, and journey date. Fares are automatically adjusted for peak seasons (December and January).

- Case-insensitive search for improved user experience
- Dynamic pricing based on seasonal demand
- Sorted results for better readability

### 3. Display All Flights

View all flights currently in the system with their complete details.

- Tabular format for easy scanning
- Sorted by flight ID for consistency

### 4. Data Validation

- Source and destination cannot be the same
- Journey date must be in the future
- Fare and seat count must be positive
- All required fields must be provided
- Input validation with helpful error messages

## Technical Highlights

- **Spring Boot Framework**: Lightweight Java application without database dependencies
- **Aspect-Oriented Programming**: For centralized logging and cross-cutting concerns
- **In-Memory Repository**: Optimized with dual indexing (by ID and by route)
- **Command Line Interface**: Interactive text-based UI with structured menus
- **Optimized Data Structures**: For improved performance and memory usage
- **Clean Code Principles**: Well-organized, documented, and maintainable codebase

## Developer Notes

- To clean up unnecessary files: `./cleanup.sh`
- Application logs are available at the INFO level
- The system automatically adds sample flights at startup
- Service layer implements comprehensive validation
- Configuration is externalized in application.properties

## Screenshots

![Main Menu](https://via.placeholder.com/800x400?text=InfyGo+Main+Menu)
![Flight Search](https://via.placeholder.com/800x400?text=Flight+Search+Results)

## Troubleshooting

### Common Issues

1. **Permission denied when running scripts**

   ```bash
   chmod +x *.sh
   ```

2. **Java version issues**
   Ensure you have Java 17+ installed and JAVA_HOME is set correctly:

   ```bash
   java -version
   echo $JAVA_HOME
   ```

3. **Maven build fails**
   Check Maven installation:
   ```bash
   mvn -version
   ```
