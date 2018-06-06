package ru.job4j.mvcservlets;

import ru.job4j.users.User;
import ru.job4j.users.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for user modification.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UsersEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", ValidateService.getInstance().findRoles());
        req.setAttribute("user", ValidateService.getInstance().findByIdView(req.getParameter("id")).get());
        req.setAttribute("countries", ValidateService.getInstance().getCountries());
        User current = ValidateService.getInstance().findByIdView(req.getParameter("id")).get();
        req.setAttribute("cities", ValidateService.getInstance().getCitiesByCountryName(current.getCountry()));
        req.getRequestDispatcher("/WEB-INF/views/UserModify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User current = ValidateService.getInstance().findById(req.getParameter("id")).get();
        String message;
        if (ValidateService.getInstance().update(current, req.getParameter("newname"), req.getParameter("newlogin"), req.getParameter("newemail"), req.getParameter("newpassword"), req.getParameter("newrole"), req.getParameter("newcountry"), req.getParameter("newcity"))) {
            message = "User modified!";
        } else {
            message = "User already exists!";
        }
        req.setAttribute("message", message);
        req.setAttribute("redirect", "");
        req.getRequestDispatcher("/WEB-INF/views/Redirect.jsp").forward(req, resp);
    }
}
