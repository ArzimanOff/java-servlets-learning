package org.arzimanoff.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arzimanoff.http.service.FlightService;
import org.arzimanoff.http.service.TicketService;
import org.arzimanoff.http.util.JspHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var flightId = Long.valueOf(req.getParameter("flightId"));
        var flightNo = req.getParameter("flightNo");

        req.setAttribute("tickets", ticketService.findAllByFlightId(flightId));
        req.setAttribute("flightNo", flightNo);

        req.getRequestDispatcher(JspHelper.getPath("tickets"))
                .forward(req, resp);
    }
}
