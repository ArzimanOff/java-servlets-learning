package org.arzimanoff.http.service;

import org.arzimanoff.http.dao.FlightDao;
import org.arzimanoff.http.dto.FlightDto;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();

    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService() {
    }

    public List<FlightDto> findAll() {
        return flightDao.findAll()
                .stream()
                .map(flight -> FlightDto
                        .builder()
                        .id(flight.getId())
                        .flightNo(flight.getFlightNo())
                        .description(
                                "%s -> %s ( %s )".formatted(
                                        flight.getDepartureAirportCode(),
                                        flight.getArrivalAirportCode(),
                                        flight.getStatus()
                                )
                        )
                        .build()
                )
                .collect(Collectors.toList());
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }
}
