package ru.job4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.domain.*;
import ru.job4j.repository.ItemDataRepository;
import ru.job4j.repository.UserDataRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Item creation spring controller.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Controller
public class ItemCreateController {

    @Autowired
    private ItemDataRepository itemRepository;
    @Autowired
    private UserDataRepository userRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreate() {
        return "item_create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createItem(@RequestParam("photo") MultipartFile file, HttpServletRequest request) {

        if (request.getParameterMap().size() == 20) {

            byte[] photo = new byte[0];
            try {
                photo = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Car car = new Car(LocalDateTime.from(LocalDate.parse(request.getParameter("manufactured"), formatter).atStartOfDay()), request.getParameter("drive"), Integer.valueOf(request.getParameter("price")));
            Body body = new Body(request.getParameter("bodytype"), request.getParameter("bodycolor"), request.getParameter("wheelside"));
            Engine engine = new Engine(request.getParameter("enginetype"), Double.valueOf(request.getParameter("enginevolume")), Integer.valueOf(request.getParameter("enginepower")), Integer.valueOf(request.getParameter("enginemilage")));
            GearBox gearBox = new GearBox(request.getParameter("gearboxtype"), Integer.valueOf(request.getParameter("gearcount")));
            Manufacturer manufacturer = new Manufacturer(request.getParameter("manufacturer"), request.getParameter("country"));

            Model model = new Model(request.getParameter("model"), LocalDateTime.from(LocalDate.parse(request.getParameter("releasedate"), formatter).atStartOfDay()), Boolean.valueOf(request.getParameter("manufacturing")));

            if (photo.length > 0) {
                car.setPhoto(photo);
            }
            car.setModel(model);
            car.setEngine(engine);
            car.setGearbox(gearBox);
            car.setBody(body);
            car.setManufacturer(manufacturer);
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String currentUserId = "";
            if (principal instanceof UserDetails) {
                currentUserId = ((UserDetails) principal).getAuthorities().toArray()[0].toString();
            }
            User user = userRepository.findById(Integer.valueOf(currentUserId)).get();
            Item item = new Item(request.getParameter("header"), request.getParameter("text"));
            item.setAuthor(user);
            item.setCar(car);

            itemRepository.save(item);
        } else {
            request.getSession().setAttribute("error", "Item create error!");
        }

        return "item_list";
    }

}
