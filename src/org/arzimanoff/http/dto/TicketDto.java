package org.arzimanoff.http.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TicketDto {
    Long id;
    String  passengerName;
    String  seatNo;
}
