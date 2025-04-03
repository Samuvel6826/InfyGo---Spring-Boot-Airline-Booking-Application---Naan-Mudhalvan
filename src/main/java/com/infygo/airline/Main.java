package com.infygo.airline;

import com.infygo.airline.domain.Flight;
import com.infygo.airline.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main command-line interface for the InfyGo Airline Booking application.
 * Provides a text-based UI for interacting with the system.
 */
@Component
public class Main implements CommandLineRunner {

    // Dependencies and configuration
    private final FlightService flightService;
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final AtomicInteger flightIdGenerator = new AtomicInteger(1000);

    @Autowired
    public Main(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * Application entry point - loads sample data and starts the UI
     */
    @Override
    public void run(String... args) {
        // Add sample flights at startup
        addSampleFlights();

        // Main program loop
        runMainMenu();

        // Clean up
        scanner.close();
    }

    /**
     * Runs the main menu loop until exit is chosen
     */
    private void runMainMenu() {
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addFlight();
                    break;
                case 2:
                    searchFlights();
                    break;
                case 3:
                    displayAllFlights();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using InfyGo. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Display the main menu options
     */
    private void displayMenu() {
        System.out.println("\n===== InfyGo Airline Booking System =====");
        System.out.println("1. Add Flight");
        System.out.println("2. Search Flights");
        System.out.println("3. Display All Flights");
        System.out.println("4. Exit");
        System.out.println("========================================");
    }

    /**
     * Workflow: Add a new flight to the system
     */
    private void addFlight() {
        System.out.println("\n----- Add New Flight -----");

        // Auto-generate FlightId
        String flightId = "FLT" + flightIdGenerator.incrementAndGet();
        System.out.println("Generated Flight ID: " + flightId);

        // Collect flight information
        String airlines = getStringInput("Enter Airlines Name: ");
        String source = getStringInput("Enter Source: ");
        String destination = getStringInput("Enter Destination: ");
        Double fare = getDoubleInput("Enter Fare: ");
        LocalDate journeyDate = getDateInput("Enter Journey Date (yyyy-MM-dd): ");
        Integer seatCount = getIntInput("Enter Seat Count: ");

        // Create and add the flight
        Flight flight = new Flight(flightId, airlines, source, destination, fare, journeyDate, seatCount);

        try {
            flightService.addFlight(flight);
            System.out.println("Flight added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Workflow: Search for flights by route and date
     */
    private void searchFlights() {
        System.out.println("\n----- Search Flights -----");

        // Collect search criteria
        String source = getStringInput("Enter Source: ");
        String destination = getStringInput("Enter Destination: ");
        LocalDate journeyDate = getDateInput("Enter Journey Date (yyyy-MM-dd): ");

        // Perform the search
        List<Flight> flights = flightService.searchFlights(source, destination, journeyDate);

        if (flights.isEmpty()) {
            System.out.println("No flights found for the given criteria.");
            return;
        }

        // Calculate peak season pricing
        boolean isPeakSeason = isPeakSeason(journeyDate);
        displaySearchResults(flights, isPeakSeason);
    }

    /**
     * Display search results with conditional peak season pricing
     */
    private void displaySearchResults(List<Flight> flights, boolean isPeakSeason) {
        if (isPeakSeason) {
            System.out.println("\nNote: Festival season rates apply (20% fare increase)");
        }

        System.out.println("\nAvailable Flights:");
        printFlightTableHeader();

        for (Flight flight : flights) {
            double displayFare = flight.getFare();
            if (isPeakSeason) {
                displayFare = displayFare * 1.2; // Apply 20% increase for display only
            }

            printFlightDetails(flight, displayFare);
        }
    }

    /**
     * Determines if a date falls in the peak season (December or January)
     */
    private boolean isPeakSeason(LocalDate date) {
        int month = date.getMonthValue();
        return month == 12 || month == 1; // December or January
    }

    /**
     * Workflow: Display all available flights
     */
    private void displayAllFlights() {
        System.out.println("\n----- All Available Flights -----");

        List<Flight> flights = flightService.getAllFlights();

        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }

        printFlightTableHeader();

        for (Flight flight : flights) {
            printFlightDetails(flight, flight.getFare());
        }
    }

    /**
     * Print the header row for flight tables
     */
    private void printFlightTableHeader() {
        System.out.printf("%-10s %-15s %-10s %-15s %-10s %-12s %-10s\n",
                "Flight ID", "Airlines", "Source", "Destination", "Fare", "Journey Date", "Seats");
        System.out.println("---------------------------------------------------------------------------------");
    }

    /**
     * Print details of a single flight
     */
    private void printFlightDetails(Flight flight, double fare) {
        System.out.printf("%-10s %-15s %-10s %-15s Rs %-9.2f %-12s %-10d\n",
                flight.getFlightId(),
                flight.getAirlines(),
                flight.getSource(),
                flight.getDestination(),
                fare,
                flight.getJourneyDate(),
                flight.getSeatCount());
    }

    /**
     * Initialize the system with sample flight data
     */
    private void addSampleFlights() {
        // Sample data using simple arrays - optimized for maintenance
        String[][] flightData = {
                // Airline, Source, Destination, Fare, Seats
                { "Air India", "Delhi", "Mumbai", "5000", "120" },
                { "IndiGo", "Mumbai", "Kolkata", "3500", "180" },
                { "SpiceJet", "Bangalore", "Chennai", "4000", "150" },
                { "Vistara", "Chennai", "Hyderabad", "6000", "100" },
                { "Air India", "Mumbai", "Delhi", "5200", "110" } // Return flight
        };

        // Dates (regular and peak season)
        LocalDate[] dates = {
                LocalDate.now().plusDays(7),
                LocalDate.now().plusDays(14),
                LocalDate.of(LocalDate.now().getYear(), 12, 25), // December (peak)
                LocalDate.of(LocalDate.now().getYear(), 1, 10), // January (peak)
                LocalDate.now().plusDays(21) // Return flight date
        };

        // Create flights from the data arrays
        for (int i = 0; i < flightData.length; i++) {
            try {
                flightService.addFlight(new Flight(
                        "FLT" + flightIdGenerator.incrementAndGet(),
                        flightData[i][0], // airline
                        flightData[i][1], // source
                        flightData[i][2], // destination
                        Double.parseDouble(flightData[i][3]), // fare
                        dates[i % dates.length], // cycle through dates if more flights than dates
                        Integer.parseInt(flightData[i][4]))); // seats
            } catch (Exception e) {
                System.err.println("Error adding sample flight: " + e.getMessage());
            }
        }
    }

    /**
     * Get a non-empty string input from the user
     */
    private String getStringInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    /**
     * Get a positive integer input from the user
     */
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Get a positive double input from the user
     */
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Get a valid future date input from the user
     */
    private LocalDate getDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                LocalDate date = LocalDate.parse(scanner.nextLine().trim(), dateFormatter);

                // Validate the date is not in the past
                LocalDate today = LocalDate.now();
                if (date.isBefore(today)) {
                    System.out.println(
                            "Error: Journey date cannot be in the past. Please enter a current or future date.");
                    continue;
                }

                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
            }
        }
    }
}
