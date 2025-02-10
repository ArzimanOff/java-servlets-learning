package org.arzimanoff.http.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Stream;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var param = req.getParameter("param");
        var parameterMap = req.getParameterMap();
        System.out.println();

        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("token", "12345");
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = resp.getWriter()) {
            writer.write("<b>Привет из первого сервлета!</b>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var reader = req.getReader();
             var lines = reader.lines();
        ) {
            lines.forEach(System.out::println);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
