package ru.job4j.servlets;

import ru.job4j.users.User;
import ru.job4j.users.ValidateService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Presentation layout for web application.
 * Servlet for users creation.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserCreateServlet extends HttpServlet {
    /**
     * Application logic singleton.
     */
    private final ValidateService logic = ValidateService.getInstance();
    /**
     * Show user creation form.
     * @param req HttpServletRequest.
     * @param resp HttpServletResponse.
     * @throws ServletException if servlet fails.
     * @throws IOException if IO failed.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<html>"
                + " <head>"
                + "  <meta charset='UTF-8'>"
                + "  <title>Users management</title>"
                + " </head>"
                + " <style>"
                + " table, th, td {"
                + "    border: 1px solid black;"
                + " }"
                + " </style>"
                + " <body>"
                + "<tr><td>Create new user:</td></tr>"
                + "<tr>"
                + "<td><form action='"
                + req.getContextPath()
                + "/create' method='POST'>"
                + "<input type='text' placeholder='Enter name' name='name'>"
                + "<input type='text' placeholder='Enter login' name='login'>"
                + "<input type='text' placeholder='Enter email' name='email'>"
                + "<input type='submit' value='Create'/></form></td>"
                + "<td><form style='margin-bottom:0;' action='"
                + req.getContextPath()
                + "/list' method='GET'/><input type='submit' value='To list'/></form></td></tr>").flush();
    }
    /**
     * Create user and show redirect dialog.
     * @param req HttpServletRequest.
     * @param resp HttpServletResponse.
     * @throws ServletException if servlet fails.
     * @throws IOException if IO failed.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User current = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        String message;
        if (logic.add(current)) {
            message = "User created!";
        } else {
            message = "User already exists!";
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<html> "
                + "<head>"
                + "<title>User created!</title>"
                + "<meta http-equiv='Refresh' content='3;" + req.getContextPath() + "/UserCreate.jsp'> "
                + "</head>"
                + "<body bgcolor='White' text='Navy'>"
                + "<p>" + message + "</p>"
                + "To users list:<a href='" + req.getContextPath() + "/UsersList.jsp'>link</a>."
                + "</body>"
                + "</html>").flush();
    }
}
