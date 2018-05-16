package ru.job4j.servlets;

import ru.job4j.users.User;
import ru.job4j.users.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Presentation layout for web application.
 * Servlet for user update.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserUpdateServlet extends HttpServlet {

    /**
     * Application logic singleton.
     */
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<html>" +
                " <head>" +
                "  <meta charset='UTF-8'>" +
                "  <title>Users management</title>" +
                " </head>" +
                " <style>" +
                " table, th, td {" +
                "    border: 1px solid black;" +
                " }" +
                " </style>" +
                " <body>" +
                "<tr><td>Modify user:</td></tr>" +
                "<tr>" +
                "<td><form action='" + req.getContextPath() + "/edit' method='POST'>" +
                "<input type='text' value='" + req.getParameter("name") + "' name='newname'>" +
                "<input type='text' value='" + req.getParameter("login") + "' name='newlogin'>" +
                "<input type='text' value='" + req.getParameter("email") + "' name='newemail'>" +
                "<input type='submit' value='Apply'/></form></td>" +
                "<td><form style='margin-bottom:0;' action='" + req.getContextPath() + "/list' method='GET'/><input type='submit' value='To list'/></form></td></tr>").flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User current = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        String message;
        if (logic.update(current, req.getParameter("newname"), req.getParameter("newlogin"), req.getParameter("newemail"))) {
            message = "User modified!";
        } else {
            message = "User not found!";
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<html> " +
                "<head>" +
                "<title>User created!</title>" +
                "<meta http-equiv='Refresh' content='3'> " +
                "</head>" +
                "<body bgcolor='White' text='Navy'>" +
                "<p>" + message + "</p>" +
                "To users list:<a href='" + req.getContextPath() + "/list'>link</a>." +
                "</body>" +
                "</html>").flush();
    }
}
