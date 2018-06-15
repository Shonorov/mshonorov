package ru.job4j.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpServlet;

/**
 * Servlet for user signing out.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.setAttribute("message", "Logging out...");
        req.setAttribute("redirect", "signin");
        req.getRequestDispatcher("/WEB-INF/views/Redirect.jsp").forward(req, resp);
    }
}

