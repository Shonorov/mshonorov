package ru.job4j.cars.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.CarsRepository;
import ru.job4j.cars.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Item list spring controller.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Controller
public class ItemListController {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    CarsRepository repository = context.getBean(CarsRepository.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "text/json")
    public List<Item> getItems(@RequestParam("filter") String filter) {
        System.out.println("list");
        List<Item> items = new ArrayList<>();
        items = repository.getAllItems();
        System.out.println(items);
        return items;
    }
}
