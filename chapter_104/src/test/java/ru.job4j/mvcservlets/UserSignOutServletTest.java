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
public class UserSignOutServletTest {

    @Test
    public void whenPostThenSignOut() throws IOException, ServletException {
        String path = "/WEB-INF/views/Redirect.jsp";
        UserSignOutServlet servlet = new UserSignOutServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);
        servlet.doPost(request, response);
        verify(dispatcher).forward(request, response);
        verify(session, atLeast(1)).invalidate();
        verify(request, atLeast(1)).setAttribute("message", "Logging out...");
        verify(request, atLeast(1)).setAttribute("redirect", "signin");
    }
}
