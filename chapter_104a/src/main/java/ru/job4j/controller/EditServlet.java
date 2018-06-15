package ru.job4j.controller;

import ru.job4j.model.RepositoryService;
import ru.job4j.model.Style;
import ru.job4j.model.StyleEntry;
import ru.job4j.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Servlet for user modification.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", RepositoryService.getInstance().getAllRoles());
        User current = RepositoryService.getInstance().findUserByID(req.getParameter("id")).get();
        req.setAttribute("user", current);
        req.setAttribute("allstyles", RepositoryService.getInstance().getAllStyles());
        req.setAttribute("userstyles", RepositoryService.getInstance().getUserStyles(current));
        req.getRequestDispatcher("/WEB-INF/views/Edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = RepositoryService.getInstance().findUserByID(req.getParameter("id")).get();
        user.setName(req.getParameter("newname"));
        user.setLogin(req.getParameter("newlogin"));
        user.setPassword(req.getParameter("newpassword"));
        user.setAddress(req.getParameter("newaddress"));
        user.setRole(req.getParameter("newrole"));
        String message;
        if (RepositoryService.getInstance().updateUser(user, isStyleChanged(user, req))) {
            RepositoryService.getInstance().updateUserStyleEntries(user, getSelectedStyle(req));
            message = "User modified!";
        } else {
            message = "User already exists!";
        }
        req.setAttribute("message", message);
        req.setAttribute("redirect", "");
        req.getRequestDispatcher("/WEB-INF/views/Redirect.jsp").forward(req, resp);
    }

    /**
     * Get selected styles from request.
     * @param req servlet request.
     * @return list of styles.
     */
    public List<Style> getSelectedStyle(HttpServletRequest req) {
        List<Style> styles = RepositoryService.getInstance().getAllStyles();
        List<Style> result = new LinkedList<>();
        for (Style style : styles) {
            if (req.getParameter(style.getStyle()) != null) {
                result.add(style);
            }
        }
        return result;
    }

    /**
     * Check is user styles changed.
     * @param user user to check.
     * @param req servlet request.
     * @return true if changed.
     */
    private boolean isStyleChanged(User user, HttpServletRequest req) {
        boolean result = true;
        List<Style> previousStyle = RepositoryService.getInstance().getUserStyles(user);
        if (previousStyle.equals(getSelectedStyle(req))) {
            result = false;
        }
        return result;
    }
}
