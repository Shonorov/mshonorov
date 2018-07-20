package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Item creation spring controller.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Controller
public class ItemCreateController {

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreate(ModelMap model) {
        System.out.println("create");
        return "item_create";
    }
}
