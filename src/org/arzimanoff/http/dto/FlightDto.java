package org.arzimanoff.http.dto;

import lombok.*;

import java.util.Objects;

@Value
@Builder
public class FlightDto {
    Long id;
    String flightNo;
    String description;
}
