package com.infygo.airline.service;

import com.infygo.airline.domain.Flight;
import com.infygo.airline.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of the FlightService interface.
 * Handles business logic for flight operations.
 */
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public void addFlight(Flight flight) {
        // Validate flight before adding
        validateFlight(flight);
        flightRepository.addFlight(flight);
    }

    @Override
    public List<Flight> searchFlights(String source, String destination, LocalDate journeyDate) {
        // Validate search parameters
        if (source == null || destination == null || journeyDate == null) {
            throw new IllegalArgumentException("Search parameters cannot be null");
        }

        // City names should be case-insensitive for better user experience
        String normalizedSource = source.trim();
        String normalizedDestination = destination.trim();

        return flightRepository.searchFlights(normalizedSource, normalizedDestination, journeyDate);
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.getAllFlights();
    }

    /**
     * Validates flight data before persistence
     */
    private void validateFlight(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("Flight cannot be null");
        }

        if (flight.getFlightId() == null || flight.getFlightId().trim().isEmpty()) {
            throw new IllegalArgumentException("Flight ID is required");
        }

        if (flight.getSource() == null || flight.getDestination() == null) {
            throw new IllegalArgumentException("Source and destination are required");
        }

        if (flight.getSource().equalsIgnoreCase(flight.getDestination())) {
            throw new IllegalArgumentException("Source and destination cannot be the same");
        }

        if (flight.getFare() == null || flight.getFare() <= 0) {
            throw new IllegalArgumentException("Fare must be greater than zero");
        }

        if (flight.getSeatCount() == null || flight.getSeatCount() <= 0) {
            throw new IllegalArgumentException("Seat count must be greater than zero");
        }
    }
}
