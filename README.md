## Architecture

The application follows a layered architecture pattern:

1. **Presentation Layer (Main.java)**: CLI interface for user interaction
2. **Service Layer**: Business logic and validation
3. **Repository Layer**: In-memory data storage with optimized indexing
4. **Domain Layer**: Entity definitions
5. **Cross-cutting Concerns**: Logging using AOP

## Performance Optimizations

- Uses HashMap for O(1) flight lookup by ID
- Implements route-based indexing for faster flight searches
- Lazy initialization for Spring components
- Efficient data structures throughout the codebase

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository: git clone https://your-repository-url/infygo-airline-booking.git cd airline-booking

2. Build the project: ./run.sh --build

### Running the Application: ./run.sh

## Features

### 1. Add Flight

Add a new flight with details like airline, source, destination, fare, journey date, and seat count.

### 2. Search Flights

Search for flights based on source, destination, and journey date. Fares are automatically adjusted for peak seasons (December and January).

### 3. Display All Flights

View all flights currently in the system with their complete details.

### 4. Data Validation

- Source and destination cannot be the same
- Journey date must be in the future
- Fare and seat count must be positive
- All required fields must be provided

## Technical Highlights

- **Spring Boot Framework**: Lightweight Java application
- **Aspect-Oriented Programming**: For centralized logging
- **In-Memory Repository**: Optimized with dual indexing
- **Command Line Interface**: Interactive text-based UI
- **Optimized Data Structures**: For improved performance

## Developer Notes

- To clean up unnecessary files: `./cleanup.sh`
- Application logs are available at the INFO level
- The system automatically adds sample flights at startup

## License

This project is licensed under the MIT License - see the LICENSE file for details.
