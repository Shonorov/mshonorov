package ru.job4j.cars.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.cars.CarsRepository;
import ru.job4j.cars.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
//        System.out.println("Hit!");
        System.out.println(req.getParameter("header"));
        System.out.println(req.getParameter("text"));
        System.out.println(req.getParameter("price"));
        System.out.println(req.getParameter("drive"));
        System.out.println(req.getParameter("manufactured"));
        System.out.println(req.getParameter("photo"));
        System.out.println(req.getParameter("manufacturer"));
        System.out.println(req.getParameter("country"));
        System.out.println(req.getParameter("model"));
        System.out.println(req.getParameter("releasedate"));
        System.out.println(req.getParameter("manufacturing"));
        System.out.println(req.getParameter("enginetype"));
        System.out.println(req.getParameter("enginevolume"));
        System.out.println(req.getParameter("enginepower"));
        System.out.println(req.getParameter("enginemilage"));
        System.out.println(req.getParameter("bodytype"));
        System.out.println(req.getParameter("bodycolor"));
        System.out.println(req.getParameter("wheelside"));
        System.out.println(req.getParameter("gearboxtype"));
        System.out.println(req.getParameter("gearcount"));
        System.out.println(req.getParameter("headertst"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Car car = new Car(LocalDateTime.from(LocalDate.parse(req.getParameter("manufactured"), formatter).atStartOfDay()), req.getParameter("drive"), Integer.valueOf(req.getParameter("price")));
        Body body = new Body(req.getParameter("bodytype"), req.getParameter("bodycolor"), req.getParameter("wheelside"));
        Engine engine = new Engine(req.getParameter("enginetype"), Double.valueOf(req.getParameter("enginevolume")), Integer.valueOf(req.getParameter("enginepower")), Integer.valueOf(req.getParameter("enginemilage")));
        GearBox gearBox = new GearBox(req.getParameter("gearboxtype"), Integer.valueOf(req.getParameter("gearcount")));
        Manufacturer manufacturer = new Manufacturer(req.getParameter("manufacturer"), req.getParameter("country"));

        List<Model> models = new ArrayList<>();
        Model model = new Model(req.getParameter("model"), LocalDateTime.from(LocalDate.parse(req.getParameter("releasedate"), formatter).atStartOfDay()), Boolean.valueOf(req.getParameter("manufacturing")));
        models.add(model);
        manufacturer.setModels(models);

//        car.setPhoto(photoUpload(req, resp));
        car.setModel(model);
        car.setEngine(engine);
        car.setGearbox(gearBox);
        car.setBody(body);
        car.setManufacturer(manufacturer);
        HttpSession session = req.getSession();
        User user = CarsRepository.getInstance().findUserByID("1").get();
//        User user = CarsRepository.getInstance().findUserByID(session.getAttribute("id").toString()).get();
        Item item = new Item(req.getParameter("header"), req.getParameter("text"));
        item.setAuthor(user);
        item.setCar(car);
        System.out.println(item);
        CarsRepository.getInstance().createItem(item);

        req.getRequestDispatcher("/WEB-INF/views/item_list.html").forward(req, resp);
    }


}
