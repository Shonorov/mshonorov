package ru.job4j.servlets;

import ru.job4j.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

/**
 * Servlet for user management web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserServlet extends HttpServlet {

    private List<User> users = new CopyOnWriteArrayList<User>();
//    private HashMap<String, Function<String, Boolean>> dispatch = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.format("%s | %s | %s | %s | %s", "id", "name", "login", "email", "createDate")).append(System.lineSeparator());
        for (User user : users) {
            writer.append(user.toString()).append(System.lineSeparator());
        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.users.add(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("User created!").flush();
    }
}
