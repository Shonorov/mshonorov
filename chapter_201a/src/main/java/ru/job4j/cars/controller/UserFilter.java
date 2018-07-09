package ru.job4j.cars.controller;


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
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (req.getRequestURI().contains("/signin") || req.getRequestURI().contains("/signout")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String id = String.valueOf(req.getSession().getAttribute("id"));
            if (req.getRequestURI().contains("/list") && req.getMethod().equals("POST") && !req.getParameter("author").equals(id)) {
                session.setAttribute("error", "Access denied!");
                resp.sendRedirect(String.format("%s/shop", req.getContextPath()));
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
