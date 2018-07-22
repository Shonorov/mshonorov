package ru.job4j.cars.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.cars.config.HibernateConfig;
import ru.job4j.cars.dao.CarsRepository;
import ru.job4j.cars.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Item creation spring controller.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Controller
public class ItemCreateController {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
    private CarsRepository repository = context.getBean(CarsRepository.class);

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreate() {
        return "item_create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createItem(HttpServletRequest request) {

        Map<String, String> param = new HashMap<>();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        Iterator<FileItem> iterator = null;
        try {
            iterator = upload.parseRequest(request).iterator();
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

        System.out.println(param);

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
        HttpSession session = request.getSession();
        User user = repository.findUserByID(session.getAttribute("id").toString()).get();
        Item item = new Item(param.get("header"), param.get("text"));
        item.setAuthor(user);
        item.setCar(car);

        System.out.println(item);

        repository.createItem(item);

        return "item_list";
    }

}
