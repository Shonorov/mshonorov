package ru.job4j.controller;

import ru.job4j.model.RepositoryService;
import ru.job4j.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Servlet for users login.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> current = RepositoryService.getInstance().authenticate(login, password);
        if (current.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("id", current.get().getId());
            session.setAttribute("role", current.get().getRole());
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Invalid credentials!");
            req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
        }
    }
}
