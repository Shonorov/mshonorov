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
 * Servlet for users output.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UsersServlet extends HttpServlet {

    /**
     * Application logic singleton.
     */
    private final ValidateService logic = ValidateService.getInstance();


    /**
     * Show all current users.
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
                + "<tr><td>All users list:</td></tr>"
                + "  <table><tr>"
                + "<td>id</td><td>name</td><td>login</td><td>email</td><td>createDate</td>"
                + "<td colspan='2'><form style='margin-bottom:0;' action='"
                + req.getContextPath()
                + "/create' method='GET'>"
                + "<input type='submit' value='Create user'/></form></td>"
                + "</tr>");
        for (User user : logic.findAll()) {
            writer.append("<tr><td>"
                    + user.getId()
                    + "</td><td>"
                    + user.getName()
                    + "</td><td>"
                    + user.getLogin()
                    + "</td><td>"
                    + user.getEmail()
                    + "</td><td>"
                    + user.getCreateDate()
                    + "</td><td><form style='margin-bottom:0;' action='"
                    + req.getContextPath()
                    + "/edit' method='GET'>"
                    + "<input type='submit' value='Edit'/>"
                    + "<input type='hidden' name='id' value='"
                    + user.getId()
                    + "'></form></td>"
                    + "<td><form style='margin-bottom:0;' action='"
                    + req.getContextPath()
                    + "/list' method='POST'>"
                    + "<input type='hidden' name='id' value='"
                    + user.getId()
                    + "'>"
                    + "<input type='submit' value='Delete'/></form></td></tr>");
        }

        writer.append("  </table>"
                + "  </div>"
                + " </body>"
                + "</html>");
        writer.flush();
    }
    /**
     * Delete selected user.
     * @param req HttpServletRequest.
     * @param resp HttpServletResponse.
     * @throws ServletException if servlet fails.
     * @throws IOException if IO failed.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User current = logic.findById(req.getParameter("id")).get();
        String message;
        if (logic.delete(current)) {
            message = "User deleted!";
        } else {
            message = "User not found!";
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<html> "
                + "<head>"
                + "<title>User created!</title>"
                + "<meta http-equiv='Refresh' content='3'> "
                + "</head>"
                + "<body bgcolor='White' text='Navy'>"
                + "<p>" + message + "</p>"
                + "To users list:<a href='"
                + req.getContextPath()
                + "/list'>link</a>."
                + "</body>"
                + "</html>").flush();
    }
}
