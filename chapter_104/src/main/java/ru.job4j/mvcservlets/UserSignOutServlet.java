package ru.job4j.mvcservlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for user signing out.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserSignOutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<html> "
                + "<head>"
                + "<title>User created!</title>"
                + "<meta http-equiv='Refresh' content='2;"
                + req.getContextPath()
                + "/signin'>"
                + "</head>"
                + "<body bgcolor='White' text='Navy'>"
                + "<p>"
                + "Sign out success!<br/>"
                + "Redirecting to sign in..."
                + "</p>"
                + "Sign in:<a href='"
                + req.getContextPath()
                + "/signin'>link</a>."
                + "</body>"
                + "</html>").flush();
    }
}
