package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Item;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Spring data repository for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface ItemDataRepository extends CrudRepository<Item, Integer> {

    List<Item> findByCarPhotoNotNull();

    List<Item> findByCarManufacturerName(String name);

    List<Item> findByCreatedGreaterThanEqual(LocalDateTime greater);
}
