package org.arzimanoff.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class CounterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;
        }
        session.setAttribute("visitCounter", visitCounter);
        String username = req.getParameter("username");

        try (PrintWriter printWriter = resp.getWriter();) {
            resp.setContentType("text/html");

            if (username == null) {
                printWriter.write("Hello, Anonymous" + "<br>");
            } else {
                printWriter.write("Hello, " + username + "<br>");
            }
            printWriter.write("Page was visited " + visitCounter + " times.");
        }
    }
}
