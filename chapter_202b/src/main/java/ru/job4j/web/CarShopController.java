package ru.job4j.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Car shop spring controller.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Controller
public class CarShopController {

    @RequestMapping (value = "/shop", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        return "item_list";
    }

}
