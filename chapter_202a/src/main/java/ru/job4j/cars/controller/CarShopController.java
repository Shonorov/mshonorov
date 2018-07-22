package ru.job4j.cars.controller;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.cars.config.HibernateConfig;
import ru.job4j.cars.dao.CarsRepository;

/**
 * Car shop spring controller.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Controller
public class CarShopController {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
    private CarsRepository repository = context.getBean(CarsRepository.class);

    @RequestMapping (value = "/shop", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        return "item_list";
    }


}
