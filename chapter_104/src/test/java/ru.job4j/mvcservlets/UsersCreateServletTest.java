package ru.job4j.mvcservlets;

import org.junit.Test;
import static org.junit.Assert.*;
import ru.job4j.users.User;
import ru.job4j.users.ValidateService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.mockito.Mockito.*;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UsersCreateServletTest {

    @Test
    public void whenGetThenForward() throws IOException, ServletException {
        String path = "/WEB-INF/views/UserCreate.jsp";
        UsersCreateServlet servlet = new UsersCreateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(dispatcher).forward(request, response);
        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, never()).getSession();
    }

    @Test
    public void whenPostThenCreateUser() throws IOException, ServletException {
        String path = "/WEB-INF/views/Redirect.jsp";
        UsersCreateServlet servlet = new UsersCreateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("role")).thenReturn("user");
        when(request.getParameter("country")).thenReturn("Russia");
        when(request.getParameter("city")).thenReturn("Moscow");
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        User expect = new User("name", "login", "email", "password", "user", "Russia", "Moscow");
        servlet.doPost(request, response);
        verify(dispatcher).forward(request, response);
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("email");
        verify(request, atLeast(1)).getParameter("password");
        verify(request, atLeast(1)).getParameter("role");
        verify(request, atLeast(1)).setAttribute("message", "User created!");
        verify(request, atLeast(1)).setAttribute("redirect", "create");
        assertTrue(ValidateService.getInstance().findAll().contains(expect));
        ValidateService.getInstance().delete(ValidateService.getInstance().findByLogin("login").get());
        assertFalse(ValidateService.getInstance().findAll().contains(expect));
    }
}
