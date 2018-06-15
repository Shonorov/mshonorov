package ru.job4j.controller;
import ru.job4j.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Servlet for user creation.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", RepositoryService.getInstance().getAllRoles());
        req.setAttribute("styles", RepositoryService.getInstance().getAllStyles());
        req.getRequestDispatcher("/WEB-INF/views/Create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addressID = RepositoryService.getInstance().createAddress(req.getParameter("address"));
        User current = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("password"), addressID, req.getParameter("role"));
        String message;
        if (RepositoryService.getInstance().createUser(current)) {
            for (Style style : getSelectedStyle(req)) {
                StyleEntry currentEntry = new StyleEntry(current.getId(), style.getId());
                RepositoryService.getInstance().createStyleEntry(currentEntry);
            }
            message = "User created!";
        } else {
            message = "User already exists!";
        }
        req.setAttribute("message", message);
        req.setAttribute("redirect", "create");
        req.getRequestDispatcher("/WEB-INF/views/Redirect.jsp").forward(req, resp);
    }

    /**
     * Get selected styles from request.
     * @param req servlet request.
     * @return list of styles.
     */
    private List<Style> getSelectedStyle(HttpServletRequest req) {
        List<Style> styles = RepositoryService.getInstance().getAllStyles();
        List<Style> result = new LinkedList<>();
        for (Style style : styles) {
            if (req.getParameter(style.getStyle()) != null) {
                result.add(style);
            }
        }
        return result;
    }

}
