package ru.job4j.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.cars.dao.ManufacturerDataRepository;
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

    @Autowired
    private ManufacturerDataRepository repository;

    @RequestMapping(value = "/manufacturers", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<Manufacturer> getAllManufacturers() {
        return (List<Manufacturer>) repository.findAll();
    }
}
