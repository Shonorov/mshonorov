package ru.job4j.mvcservlets;

import org.junit.Test;
import javax.servlet.RequestDispatcher;
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
public class UsersSignInServletTest {

    @Test
    public void whenGetThenForward() throws IOException, ServletException {
        String path = "/WEB-INF/views/UserSignIn.jsp";
        UsersSignInServlet servlet = new UsersSignInServlet();
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
    public void whenPostThenAuthenticate() throws IOException, ServletException {
        UsersSignInServlet servlet = new UsersSignInServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("login")).thenReturn("guest");
        when(request.getParameter("password")).thenReturn("guest");
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        verify(response, atLeast(1)).sendRedirect(String.format("%s/", request.getContextPath()));
        verify(session, atLeast(1)).setAttribute("login", "guest");
        verify(session, atLeast(1)).setAttribute("id", "1");
        verify(session, atLeast(1)).setAttribute("role", "user");
    }

    @Test
    public void whenPostThenDoNotAuthenticate() throws IOException, ServletException {
        String path = "/WEB-INF/views/UserSignIn.jsp";
        UsersSignInServlet servlet = new UsersSignInServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getParameter("login")).thenReturn("fail");
        when(request.getParameter("password")).thenReturn("fail");
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        servlet.doPost(request, response);
        verify(dispatcher, atLeast(1)).forward(request, response);
        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, atLeast(1)).setAttribute("error", "Invalid credentials!");
    }
}