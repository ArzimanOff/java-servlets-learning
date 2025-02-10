package org.arzimanoff.http.dao;

import org.arzimanoff.http.entity.Flight;
import org.arzimanoff.http.entity.FlightStatus;
import org.arzimanoff.http.entity.Ticket;
import org.arzimanoff.http.util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, Ticket> {

    private static final TicketDao INSTANCE = new TicketDao();

    private static final String SQL_FIND_ALL_BY_FLIGHT_ID = """
            SELECT *
            FROM ticket
            where flight_id = ?;
            """;

    private TicketDao() {
    }

    public List<Ticket> findAllByFlightId(Long flightId){
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SQL_FIND_ALL_BY_FLIGHT_ID);
        ) {
            preparedStatement.setObject(1, flightId);
            List<Ticket> tickets = new ArrayList<>();
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                tickets.add(buildTicket(resultSet));
            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public Ticket save(Ticket entity) {
        return null;
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    private Ticket buildTicket(ResultSet resultSet) throws SQLException {
        return new Ticket(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("passenger_no", String.class),
                resultSet.getObject("passenger_name", String.class),
                resultSet.getObject("flight_id", Long.class),
                resultSet.getObject("seat_no", String.class),
                resultSet.getObject("cost", BigDecimal.class)
        );
    }
}
