package ru.job4j.cars.dao;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.cars.model.Manufacturer;

/**
 * Spring data repository for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface ManufacturerDataRepository extends CrudRepository<Manufacturer, Integer> {
}
