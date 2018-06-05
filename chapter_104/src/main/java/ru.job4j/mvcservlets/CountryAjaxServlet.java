package ru.job4j.mvcservlets;
import ru.job4j.users.City;
import ru.job4j.users.User;
import ru.job4j.users.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet for user creation.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CountryAjaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String country = req.getParameter("country");
//        String country = req.getAttribute("country").toString();
        System.out.println(country);
        List<City> cities = ValidateService.getInstance().getCitiesByCountryID(country);
        writer.append("[{\"name\":\"Moscow\"}, {\"name\":\"Kiev\"}, {\"name\":\"Minsk\"}]");
        writer.flush();
    }
}
