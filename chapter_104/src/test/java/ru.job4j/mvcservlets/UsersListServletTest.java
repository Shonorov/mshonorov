package ru.job4j.mvcservlets;

import org.junit.Test;
import ru.job4j.users.User;
import ru.job4j.users.ValidateService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UsersListServletTest {

    @Test
    public void whenGetThenForward() throws IOException, ServletException {
        String path = "/WEB-INF/views/UsersList.jsp";
        UsersListServlet servlet = new UsersListServlet();
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
    public void whenPostThenDelete() throws IOException, ServletException {
        String path = "/WEB-INF/views/Redirect.jsp";
        UsersListServlet servlet = new UsersListServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getParameter("id")).thenReturn("21");
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        User user = new User("21", "name", "login", "email", LocalDateTime.now(), "password", "user", "Russia", "Moscow");
        ValidateService.getInstance().add(user);
        servlet.doPost(request, response);
        verify(dispatcher).forward(request, response);
        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).setAttribute("message", "User deleted!");
        verify(request, atLeast(1)).setAttribute("redirect", "");
        assertFalse(ValidateService.getInstance().findAll().contains(user));
    }
}
