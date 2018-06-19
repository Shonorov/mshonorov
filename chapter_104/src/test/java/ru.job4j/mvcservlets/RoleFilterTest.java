package ru.job4j.mvcservlets;

import org.junit.Test;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static org.mockito.Mockito.*;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class RoleFilterTest {

    @Test
    public void whenSignInServletThenDoFilter() throws IOException, ServletException {
        RoleFilter filter = new RoleFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(request.getRequestURI()).thenReturn("/signin");
        filter.doFilter(request, response, filterChain);
        verify(filterChain, atLeast(1)).doFilter(request, response);
    }

    @Test
    public void whenNotUserCallAnyServletThenDoFilter() throws IOException, ServletException {
        RoleFilter filter = new RoleFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getRequestURI()).thenReturn("/");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("administrator");
        when(session.getAttribute("id")).thenReturn("0");
        filter.doFilter(request, response, filterChain);
        verify(filterChain, atLeast(1)).doFilter(request, response);
    }

    @Test
    public void whenUserCallCreateServletThenDenied() throws IOException, ServletException {
        RoleFilter filter = new RoleFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("user");
        when(session.getAttribute("id")).thenReturn("1");
        when(request.getRequestURI()).thenReturn("/create");
        filter.doFilter(request, response, filterChain);
        verify(response, atLeast(1)).sendRedirect(String.format("%s/", request.getContextPath()));
        verify(session, atLeast(1)).setAttribute("error", "Access denied!");
    }

    @Test
    public void whenUserCallEditServletForOtherUserThenDenied() throws IOException, ServletException {
        RoleFilter filter = new RoleFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("user");
        when(session.getAttribute("id")).thenReturn("1");
        when(request.getParameter("id")).thenReturn("2");
        when(request.getRequestURI()).thenReturn("/edit");
        filter.doFilter(request, response, filterChain);
        verify(response, atLeast(1)).sendRedirect(String.format("%s/", request.getContextPath()));
        verify(session, atLeast(1)).setAttribute("error", "Access denied!");
    }

    @Test
    public void whenUserCallEditServletForSelfAndChangeRoleToAdminThenDenied() throws IOException, ServletException {
        RoleFilter filter = new RoleFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("user");
        when(session.getAttribute("id")).thenReturn("1");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getMethod()).thenReturn("POST");
        when(request.getParameter("newrole")).thenReturn("administrator");
        when(request.getRequestURI()).thenReturn("/edit");
        filter.doFilter(request, response, filterChain);
        verify(response, atLeast(1)).sendRedirect(String.format("%s/", request.getContextPath()));
        verify(session, atLeast(1)).setAttribute("error", "Access denied!");
    }

    @Test
    public void whenUserDeleteThenDenied() throws IOException, ServletException {
        RoleFilter filter = new RoleFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("user");
        when(session.getAttribute("id")).thenReturn("1");
        when(request.getMethod()).thenReturn("POST");
        when(request.getRequestURI()).thenReturn("/users/");
        filter.doFilter(request, response, filterChain);
        verify(response, atLeast(1)).sendRedirect(String.format("%s/", request.getContextPath()));
        verify(session, atLeast(1)).setAttribute("error", "Access denied!");
    }
}
