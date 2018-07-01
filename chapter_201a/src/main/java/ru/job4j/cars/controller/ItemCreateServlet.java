package ru.job4j.cars.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Item list management servlet.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ItemCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/item_create.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hit!");
        System.out.println(req.getAttribute("header"));
        System.out.println(req.getAttribute("text"));
        System.out.println(req.getAttribute("price"));
        System.out.println(req.getAttribute("drive"));
        System.out.println(req.getAttribute("manufactured"));
        System.out.println(req.getAttribute("photo"));
        System.out.println(req.getAttribute("manufacturer"));
        System.out.println(req.getAttribute("country"));
        System.out.println(req.getAttribute("model"));
        System.out.println(req.getAttribute("releasedate"));
        System.out.println(req.getAttribute("manufacturing"));
        System.out.println(req.getAttribute("enginetype"));
        System.out.println(req.getAttribute("enginevolume"));
        System.out.println(req.getAttribute("enginepower"));
        System.out.println(req.getAttribute("enginemilage"));
        System.out.println(req.getAttribute("bodytype"));
        System.out.println(req.getAttribute("bodycolor"));
        System.out.println(req.getAttribute("wheelside"));
        System.out.println(req.getAttribute("gearboxtype"));
        System.out.println(req.getAttribute("gearcount"));



        req.getRequestDispatcher("/WEB-INF/views/item_list.html").forward(req, resp);
    }
}
