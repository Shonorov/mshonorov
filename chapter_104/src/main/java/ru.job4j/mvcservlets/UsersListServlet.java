package ru.job4j.mvcservlets;

import ru.job4j.users.User;
import ru.job4j.users.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for users output.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UsersListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("login") == null) {
            resp.sendRedirect(String.format("%s/signin", req.getContextPath()));
        } else {
            req.setAttribute("users", ValidateService.getInstance().findAll());
            req.getRequestDispatcher("/WEB-INF/views/UsersList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User current = ValidateService.getInstance().findById(req.getParameter("id")).get();
        String message;
        if (ValidateService.getInstance().delete(current)) {
            message = "User deleted!";
        } else {
            message = "User not found!";
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<html> "
                + "<head>"
                + "<title>User created!</title>"
                + "<meta http-equiv='Refresh' content='2'> "
                + "</head>"
                + "<body bgcolor='White' text='Navy'>"
                + "<p>" + message + "</p>"
                + "To users list:<a href='"
                + req.getContextPath()
                + "/'>link</a>."
                + "</body>"
                + "</html>").flush();
    }
}
