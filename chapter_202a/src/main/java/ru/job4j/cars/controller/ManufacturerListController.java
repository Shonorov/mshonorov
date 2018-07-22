package ru.job4j.cars.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.cars.config.HibernateConfig;
import ru.job4j.cars.dao.CarsRepository;
import ru.job4j.cars.model.Manufacturer;

import java.util.List;

/**
 * Manufacturer list spring controller.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Controller
public class ManufacturerListController {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
    private CarsRepository repository = context.getBean(CarsRepository.class);

    @RequestMapping(value = "/manufacturers", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<Manufacturer> getAllManufacturers() {
        return repository.getAllManufacturers();
    }
}
