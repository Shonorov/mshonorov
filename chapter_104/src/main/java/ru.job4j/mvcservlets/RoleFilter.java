package ru.job4j.mvcservlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User role filter servlet.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class RoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //TODO Role filter.
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String role = (String) req.getSession().getAttribute("role");
        String id = (String) req.getSession().getAttribute("id");
        System.out.println("Session " + session.getAttribute("id"));
        System.out.println("Request1 " + req.getAttribute("id"));
        System.out.println("Request2 " + servletRequest.getAttribute("id"));

        if (role.equals("user")) {
            if (req.getRequestURI().contains("/create")) {
                req.setAttribute("error", "Access denied!");
                resp.sendRedirect(String.format("%s/", req.getContextPath()));
            }
            if (req.getRequestURI().contains("/edit") && !id.equals(req.getAttribute("id"))) {
                req.setAttribute("error", "Access denied!");
                resp.sendRedirect(String.format("%s/", req.getContextPath()));
            }
            if (req.getRequestURI().contains("/delete")) {
                req.setAttribute("error", "Access denied!");
                resp.sendRedirect(String.format("%s/", req.getContextPath()));
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
