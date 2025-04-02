package com.infygo.airline.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Flight domain object representing a flight with all its details.
 * Implements proper equality methods for collections usage.
 */
public class Flight {
    private String flightId;
    private String airlines;
    private String source;
    private String destination;
    private Double fare;
    private LocalDate journeyDate;
    private Integer seatCount;

    // Default constructor for frameworks
    public Flight() {
    }

    /**
     * Complete constructor for creating a flight with all details.
     */
    public Flight(String flightId, String airlines, String source, String destination,
            Double fare, LocalDate journeyDate, Integer seatCount) {
        this.flightId = flightId;
        this.airlines = airlines;
        this.source = source;
        this.destination = destination;
        this.fare = fare;
        this.journeyDate = journeyDate;
        this.seatCount = seatCount;
    }

    // Getters and Setters
    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getAirlines() {
        return airlines;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    /**
     * Implementing equals for proper collection usage
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Flight flight = (Flight) o;
        return Objects.equals(flightId, flight.flightId);
    }

    /**
     * Implementing hashCode for proper collection usage
     */
    @Override
    public int hashCode() {
        return Objects.hash(flightId);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId='" + flightId + '\'' +
                ", airlines='" + airlines + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", fare=" + fare +
                ", journeyDate=" + journeyDate +
                ", seatCount=" + seatCount +
                '}';
    }
}
