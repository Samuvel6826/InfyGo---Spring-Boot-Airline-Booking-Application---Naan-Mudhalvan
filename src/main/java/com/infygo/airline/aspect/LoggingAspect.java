package com.infygo.airline.aspect;

import com.infygo.airline.domain.Flight;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for cross-cutting logging concerns.
 * Uses AOP to inject logging at key points in the application.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Log before adding a flight
     */
    @Before("execution(* com.infygo.airline.service.FlightService.addFlight(..)) && args(flight)")
    public void logBeforeAddFlight(Flight flight) {
        if (flight != null) {
            logger.info("Adding flight: {} from {} to {} on {}",
                    flight.getFlightId(),
                    flight.getSource(),
                    flight.getDestination(),
                    flight.getJourneyDate());
        }
    }

    /**
     * Log after successfully adding a flight
     */
    @After("execution(* com.infygo.airline.service.FlightService.addFlight(..))")
    public void logAfterAddFlight(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof Flight) {
            Flight flight = (Flight) args[0];
            logger.info("Flight added successfully: {}", flight.getFlightId());
        }
    }

    /**
     * Log before searching for flights
     */
    @Before("execution(* com.infygo.airline.service.FlightService.searchFlights(..)) && args(source, destination, journeyDate)")
    public void logBeforeSearchFlights(String source, String destination, Object journeyDate) {
        logger.info("Searching flights from {} to {} on {}", source, destination, journeyDate);
    }
}
