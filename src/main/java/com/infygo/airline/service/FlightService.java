package com.infygo.airline.service;

import com.infygo.airline.domain.Flight;
import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for flight-related business operations.
 */
public interface FlightService {
    /**
     * Add a new flight to the system
     * 
     * @param flight The flight to add
     */
    void addFlight(Flight flight);

    /**
     * Search for flights based on route and date
     * 
     * @param source      Origin city
     * @param destination Destination city
     * @param journeyDate Date of travel
     * @return List of matching flights
     */
    List<Flight> searchFlights(String source, String destination, LocalDate journeyDate);

    /**
     * Get all flights in the system
     * 
     * @return List of all available flights
     */
    List<Flight> getAllFlights();
}
