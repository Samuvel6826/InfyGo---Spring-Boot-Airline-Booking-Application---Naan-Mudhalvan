package com.infygo.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Main entry point for the InfyGo Airline Booking application.
 * Configured to run without a database and with AOP support.
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableAspectJAutoProxy
public class AirlineBookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(AirlineBookingApplication.class, args);
    }
}
