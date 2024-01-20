package org.example;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;



@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    private String dateFormat;

    @Override
    public void init() throws ServletException {
        dateFormat = OffsetDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        String timezone = req.getParameter("timezone");
        ZoneId zoneId = ZoneId.of(timezone != null ? timezone : "UTC");
        String currentTime = OffsetDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        resp.getWriter().write("Time now: " + currentTime + " " + zoneId.getId());
        resp.getWriter().close();
    }

    @Override
    public void destroy() {
        dateFormat = null;
    }
}
