package ru.job4j.controller;
import ru.job4j.model.RepositoryService;
import ru.job4j.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for users list.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        if (search != null) {
            req.setAttribute("users", RepositoryService.getInstance().findUsersByFilter(search));
        } else {
            req.setAttribute("users", RepositoryService.getInstance().getAllUsers());
        }
        req.getRequestDispatcher("/WEB-INF/views/List.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User current = RepositoryService.getInstance().findUserByID(req.getParameter("id")).get();
        String message;
        if (RepositoryService.getInstance().deleteUser(current)) {
            message = "User deleted!";
        } else {
            message = "User not found!";
        }
        req.setAttribute("message", message);
        req.setAttribute("redirect", "");
        req.getRequestDispatcher("/WEB-INF/views/Redirect.jsp").forward(req, resp);
    }
}
