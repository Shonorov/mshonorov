package ru.job4j.mvcservlets;

import ru.job4j.users.User;
import ru.job4j.users.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for user creation.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UsersCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", ValidateService.getInstance().findRoles());
        req.getRequestDispatcher("/WEB-INF/views/UserCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User current = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"), req.getParameter("role"));
        String message;
        if (ValidateService.getInstance().add(current)) {
            message = "User created!";
        } else {
            message = "User already exists!";
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<html> "
                + "<head>"
                + "<title>User created!</title>"
                + "<meta http-equiv='Refresh' content='2;" + req.getContextPath() + "/create'> "
                + "</head>"
                + "<body bgcolor='White' text='Navy'>"
                + "<p>" + message + "</p>"
                + "To users list:<a href='" + req.getContextPath() + "/'>link</a>."
                + "</body>"
                + "</html>").flush();
    }
}
