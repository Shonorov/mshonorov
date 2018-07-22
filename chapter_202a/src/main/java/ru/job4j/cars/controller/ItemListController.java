package ru.job4j.cars.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.cars.config.HibernateConfig;
import ru.job4j.cars.config.SpringDataConfig;
import ru.job4j.cars.dao.CarsRepository;
import ru.job4j.cars.dao.ItemDataRepository;
import ru.job4j.cars.model.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Item list spring controller.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Controller
public class ItemListController {

//    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
//    private CarsRepository repository = context.getBean(CarsRepository.class);

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDataConfig.class);
    private ItemDataRepository repository = context.getBean(ItemDataRepository.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Item> getItems(@RequestParam("filter") String filter) {
        String key = filter.contains("manufacturer") ? "manufacturer" : filter;
        Map<String, Supplier<List<Item>>> filterMap = new HashMap<String, Supplier<List<Item>>>() {
            {
                put("all", () -> (List<Item>) repository.findAll());
                put("lastday", () -> repository.findByCreatedGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
                put("photoonly", () -> repository.findByCarPhotoNotNull());
                put("manufacturer", () -> repository.findByCarManufacturerName(filter.substring(14, filter.length())));
            }
        };
        return filterMap.get(key).get();
    }

    @RequestMapping (value = "/list", method = RequestMethod.POST)
    public String changeStatus(@RequestParam("id") String id, @RequestParam("status") String status, HttpServletRequest request) {
        Optional<Item> result = repository.findById(Integer.valueOf(id));
        HttpSession session = request.getSession();
        String currentUserId = session.getAttribute("id").toString();
        if (result.isPresent() && String.valueOf(result.get().getAuthor().getId()).equals(currentUserId)) {
            Item update = repository.findById(Integer.valueOf(id)).get();
            update.setSold(Boolean.valueOf(status));
            repository.save(update);
        }
        return "item_list";
    }
}
