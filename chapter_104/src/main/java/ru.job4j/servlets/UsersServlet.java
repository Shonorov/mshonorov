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
                "  <table><tr>" +
                "<td>id</td><td>name</td><td>login</td><td>email</td><td>createDate</td>" +
                "<td><form action='" + req.getContextPath() + "/create' method='GET'><input type='submit' value='Create'/></form></td>" +
                "</tr>");
        //TODO http://jkorpela.fi/forms/tables.html
        for (User user : logic.findAll()) {
            writer.append("<tr><td>" +
                    user.getId() +
                    "</td><td>" +
                    user.getName() +
                    "</td><td>" +
                    user.getLogin() +
                    "</td><td>" +
                    user.getEmail() +
                    "</td><td>" +
                    user.getCreateDate() +
                    "</td><td><form action='" + req.getContextPath() + "/edit' method='GET'><input type='submit' value='Edit'/></form></td>" +
                    "<td><form action='" + req.getContextPath() + "/list' method='POST'><input type='submit' value='Delete'/></form></td></tr>");
        }

        writer.append("  </table>" +
                "  </div>" +
                " </body>" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        logic.delete();
        doGet(req, resp);
    }
}
