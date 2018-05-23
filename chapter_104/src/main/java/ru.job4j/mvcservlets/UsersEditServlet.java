package ru.job4j.mvcservlets;

import ru.job4j.users.User;
import ru.job4j.users.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for user modification.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UsersEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", ValidateService.getInstance().findRoles());
        req.setAttribute("user", ValidateService.getInstance().findById(req.getParameter("id")).get());
        req.getRequestDispatcher("/WEB-INF/views/UserModify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User current = ValidateService.getInstance().findById(req.getParameter("id")).get();
        String message;
        if (ValidateService.getInstance().update(current, req.getParameter("newname"), req.getParameter("newlogin"), req.getParameter("newemail"), req.getParameter("newpassword"), req.getParameter("newrole"))) {
            message = "User modified!";
        } else {
            message = "User already exists!";
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<html> "
                + "<head>"
                + "<title>User created!</title>"
                + "<meta http-equiv='Refresh' content='2;"
                + req.getContextPath()
                + "/'>"
                + "</head>"
                + "<body bgcolor='White' text='Navy'>"
                + "<p>"
                + message
                + "</p>"
                + "To users list:<a href='"
                + req.getContextPath()
                + "/'>link</a>."
                + "</body>"
                + "</html>").flush();
    }
}
