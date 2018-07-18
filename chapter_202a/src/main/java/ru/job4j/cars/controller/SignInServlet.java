package ru.job4j.cars.controller;

import ru.job4j.cars.CarsRepository;
import ru.job4j.cars.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * User sign in servlet.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/signin.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            Optional<User> current = CarsRepository.getInstance().authenticate(login, password);
            if (current.isPresent()) {
                HttpSession session = req.getSession();
                session.setAttribute("login", login);
                session.setAttribute("id", current.get().getId());
                resp.sendRedirect(String.format("%s/shop", req.getContextPath()));
            } else {
                resp.setContentType("text/html");
                PrintWriter out = resp.getWriter();
                String user = "Wrong credentials!";
                out.write(user);
                req.getRequestDispatcher("/WEB-INF/views/signin.html").forward(req, resp);
            }
        } else {
            HttpSession session = req.getSession();
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            String user = "Current user : " + session.getAttribute("login");
            out.write(user);
        }

    }

}
