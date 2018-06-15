package ru.job4j.controller;
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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        if (req.getRequestURI().contains("/signin") || req.getRequestURI().contains("/signout")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String role = (String) req.getSession().getAttribute("role");
            String id = (String) req.getSession().getAttribute("id");
            if (role.equals("user")) {
                if (req.getRequestURI().contains("/create")) {
                    session.setAttribute("error", "Access denied!");
                    resp.sendRedirect(String.format("%s/", req.getContextPath()));
                    return;
                }
                if (req.getRequestURI().contains("/edit")) {
                    if (!id.equals(req.getParameter("id"))) {
                        session.setAttribute("error", "Access denied!");
                        resp.sendRedirect(String.format("%s/", req.getContextPath()));
                        return;
                    }

                    if (req.getMethod().equals("POST") && !req.getParameter("newrole").equals("user")) {
                        session.setAttribute("error", "Access denied!");
                        resp.sendRedirect(String.format("%s/", req.getContextPath()));
                        return;
                    }
                }
                if (req.getRequestURI().equals("/music/") && req.getMethod().equals("POST")) {
                    session.setAttribute("error", "Access denied!");
                    resp.sendRedirect(String.format("%s/", req.getContextPath()));
                    return;
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
