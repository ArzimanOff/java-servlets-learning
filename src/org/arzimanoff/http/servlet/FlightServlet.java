package org.arzimanoff.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arzimanoff.http.service.FlightService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


@WebServlet("/flights")
public class FlightServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Список перелётов:</h1>");
            printWriter.write("<ul>");
            flightService.findAll().forEach(
                    flightDto -> printWriter.write(
                            """
                            <li>
                                <b>ID = %d </b>
                                Рейс: <a href="/tickets?flightId=%d&flightNo=%s">%s<a> %s
                            </li>
                            """
                            .formatted(flightDto.getId(), flightDto.getId(), flightDto.getFlightNo(), flightDto.getFlightNo(), flightDto.getDescription()))
            );
            printWriter.write("</ul>");
        }
    }
}
