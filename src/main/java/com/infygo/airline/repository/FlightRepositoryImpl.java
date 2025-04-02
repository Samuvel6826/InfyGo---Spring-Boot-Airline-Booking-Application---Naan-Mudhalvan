package com.infygo.airline.repository;

import com.infygo.airline.domain.Flight;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * In-memory implementation of the FlightRepository interface.
 * Uses optimized data structures for improved search performance.
 */
@Repository
public class FlightRepositoryImpl implements FlightRepository {

    // Primary storage for all flights - O(1) lookup by ID
    private final Map<String, Flight> flightMap = new HashMap<>();

    // Index for faster route-based search - O(1) lookup for source-destination
    // pairs
    private final Map<String, List<Flight>> routeIndex = new HashMap<>();

    @Override
    public void addFlight(Flight flight) {
        // Add to main storage
        flightMap.put(flight.getFlightId(), flight);

        // Update route index for faster searching
        String routeKey = createRouteKey(flight.getSource(), flight.getDestination());
        routeIndex.computeIfAbsent(routeKey, k -> new ArrayList<>()).add(flight);
    }

    @Override
    public List<Flight> searchFlights(String source, String destination, LocalDate journeyDate) {
        // Use the route index for faster lookup - O(n) where n is only flights on this
        // route
        String routeKey = createRouteKey(source, destination);
        List<Flight> routeFlights = routeIndex.getOrDefault(routeKey, new ArrayList<>());

        // Filter by date - this is now much faster as we're only searching relevant
        // flights
        return routeFlights.stream()
                .filter(flight -> flight.getJourneyDate().equals(journeyDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getAllFlights() {
        // Return a new list to prevent external modification of our storage
        return new ArrayList<>(flightMap.values());
    }

    /**
     * Creates a unique key for route indexing
     */
    private String createRouteKey(String source, String destination) {
        return source.toLowerCase() + "-" + destination.toLowerCase();
    }
}
