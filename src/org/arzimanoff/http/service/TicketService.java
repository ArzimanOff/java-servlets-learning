package org.arzimanoff.http.service;

import org.arzimanoff.http.dao.TicketDao;
import org.arzimanoff.http.dto.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    private static final TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();

    private TicketService() {
    }

    public List<TicketDto> findAllByFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId)
                .stream()
                .map(ticket -> TicketDto.builder()
                        .id(ticket.getId())
                        .seatNo(ticket.getSeatNo())
                        .passengerName(ticket.getPassengerName())
                        .build()
                )

                .collect(Collectors.toList());
    }

    public static TicketService getInstance() {
        return INSTANCE;
    }
}
