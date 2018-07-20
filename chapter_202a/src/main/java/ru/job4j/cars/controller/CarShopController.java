package ru.job4j.cars.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.cars.CarsRepository;
import ru.job4j.cars.model.Item;

/**
 * Car shop spring controller.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Controller
public class CarShopController {

    @Autowired
    private CarsRepository repository;

    @RequestMapping (value = "/shop", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        System.out.println("shop");
        for (Item item : repository.getAllItems()) {
            System.out.println(item);
        }
        model.addAttribute("list", "qwerty");
        return "item_list";
    }
}
