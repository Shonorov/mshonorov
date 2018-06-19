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
public class AuthFilterTest {

    @Test
    public void whenSignInServletThenDoFilter() throws IOException, ServletException {
        AuthFilter filter = new AuthFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(request.getRequestURI()).thenReturn("/signin");
        filter.doFilter(request, response, filterChain);
        verify(filterChain, atLeast(1)).doFilter(request, response);

    }

    @Test
    public void whenOtherServletAndSessionNullThenRedirect() throws IOException, ServletException {
        AuthFilter filter = new AuthFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getRequestURI()).thenReturn("/");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(null);
        filter.doFilter(request, response, filterChain);
        verify(response, atLeast(1)).sendRedirect(String.format("%s/signin", request.getContextPath()));

    }

    @Test
    public void whenOtherServletAndSessionNotNullThenDoFilter() throws IOException, ServletException {
        AuthFilter filter = new AuthFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getRequestURI()).thenReturn("/");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn("0");
        filter.doFilter(request, response, filterChain);
        verify(filterChain, atLeast(1)).doFilter(request, response);

    }
}
