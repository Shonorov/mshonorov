package ru.job4j.cars.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.cars.CarsRepository;
import ru.job4j.cars.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

        Map<String, String> param = new HashMap<>();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        Iterator<FileItem> iterator = null;
        try {
            iterator = upload.parseRequest(req).iterator();
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        byte[] photo = new byte[0];
        while (iterator.hasNext()) {
            FileItem item = iterator.next();
            if (!item.isFormField()) {
                photo = item.get();
            } else {
                param.put(item.getFieldName(), item.getString());
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Car car = new Car(LocalDateTime.from(LocalDate.parse(param.get("manufactured"), formatter).atStartOfDay()), param.get("drive"), Integer.valueOf(param.get("price")));
        Body body = new Body(param.get("bodytype"), param.get("bodycolor"), param.get("wheelside"));
        Engine engine = new Engine(param.get("enginetype"), Double.valueOf(param.get("enginevolume")), Integer.valueOf(param.get("enginepower")), Integer.valueOf(param.get("enginemilage")));
        GearBox gearBox = new GearBox(param.get("gearboxtype"), Integer.valueOf(param.get("gearcount")));
        Manufacturer manufacturer = new Manufacturer(param.get("manufacturer"), param.get("country"));

        Model model = new Model(param.get("model"), LocalDateTime.from(LocalDate.parse(param.get("releasedate"), formatter).atStartOfDay()), Boolean.valueOf(param.get("manufacturing")));

        car.setPhoto(photo);
        car.setModel(model);
        car.setEngine(engine);
        car.setGearbox(gearBox);
        car.setBody(body);
        car.setManufacturer(manufacturer);
        HttpSession session = req.getSession();
        User user = CarsRepository.getInstance().findUserByID(session.getAttribute("id").toString()).get();
        Item item = new Item(param.get("header"), param.get("text"));
        item.setAuthor(user);
        item.setCar(car);
        CarsRepository.getInstance().createItem(item);

        req.getRequestDispatcher("/WEB-INF/views/item_list.html").forward(req, resp);
    }



}
