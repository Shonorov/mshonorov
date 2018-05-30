package ru.job4j.mvcservlets;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.users.User;
import ru.job4j.users.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UsersSignInServletTest {

    @Test
    public void whenGetThenForward() throws ServletException, IOException {
        String path = "/WEB-INF/views/UserSignIn.jsp";
        UsersSignInServlet servlet = new UsersSignInServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void whenPostThenAuthenticate() {
        UsersSignInServlet servlet = new UsersSignInServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("login")).thenReturn("guest");
        when(request.getParameter("password")).thenReturn("guest");
        when(request.getSession()).thenReturn(session);
        try {
            servlet.doPost(request, response);
            verify(response, atLeast(1)).sendRedirect(String.format("%s/", request.getContextPath()));
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        verify(session, atLeast(1)).setAttribute("login", "guest");
        verify(session, atLeast(1)).setAttribute("id", "1");
        verify(session, atLeast(1)).setAttribute("role", "user");
    }

    //TODO
    @Test
    public void whenPostThenDoNotAuthenticate() {
        String path = "/WEB-INF/views/UserSignIn.jsp";
        UsersSignInServlet servlet = mock(UsersSignInServlet.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getParameter("login")).thenReturn("fail");
        when(request.getParameter("password")).thenReturn("fail");

        try {
            when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
            servlet.doPost(request, response);
            verify(servlet, atLeast(1)).doGet(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        verify(request, atLeast(1)).setAttribute("error","Invalid credentials!");

    }
}