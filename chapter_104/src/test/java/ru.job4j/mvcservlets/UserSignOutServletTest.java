package ru.job4j.mvcservlets;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.users.User;
import ru.job4j.users.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserSignOutServletTest {

    @Test
    public void whenPostThenSignOut() {
        //TODO
    }
}
