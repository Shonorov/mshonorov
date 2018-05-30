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
public class UsersEditServletTest {

    @Test
    public void whenGetThenForward() throws ServletException, IOException {
        String path = "/WEB-INF/views/UsersList.jsp";
        UsersEditServlet servlet = new UsersEditServlet();
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
    public void whenPostThenUpdate() {
        String path = "/WEB-INF/views/Redirect.jsp";
        UsersEditServlet servlet = new UsersEditServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getParameter("id")).thenReturn("20");
        when(request.getParameter("newname")).thenReturn("newname");
        when(request.getParameter("newlogin")).thenReturn("newlogin");
        when(request.getParameter("newemail")).thenReturn("newemail");
        when(request.getParameter("newpassword")).thenReturn("newpassword");
        when(request.getParameter("newrole")).thenReturn("user");
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        User add = new User("20", "name", "login", "email", LocalDateTime.now(), "password", "user");
        User replace = new User("newname", "newlogin", "newemail", "newpassword", "user");
        ValidateService.getInstance().add(add);
        try {
            servlet.doPost(request, response);
            verify(dispatcher).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        verify(request, atLeast(1)).getParameter("newname");
        verify(request, atLeast(1)).getParameter("newlogin");
        verify(request, atLeast(1)).getParameter("newemail");
        verify(request, atLeast(1)).getParameter("newpassword");
        verify(request, atLeast(1)).getParameter("newrole");
        verify(request, atLeast(1)).setAttribute("message","User modified!");
        verify(request, atLeast(1)).setAttribute("redirect","edit");
        assertTrue(ValidateService.getInstance().findAll().contains(replace));
        ValidateService.getInstance().delete(ValidateService.getInstance().findByLogin("newlogin").get());
        assertFalse(ValidateService.getInstance().findAll().contains(replace));
    }
}
