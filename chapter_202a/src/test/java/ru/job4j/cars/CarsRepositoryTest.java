package ru.job4j.cars;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.job4j.cars.config.SpringDataConfig;
import ru.job4j.cars.dao.ItemDataRepository;
import ru.job4j.cars.dao.UserDataRepository;
import ru.job4j.cars.model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarsRepositoryTest {

    @Test
    public void whenCreateAllEntitiesThenFind() {
        String str = "01-01-2010 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDataConfig.class);
        ItemDataRepository itemRepository = context.getBean(ItemDataRepository.class);
        Car car = new Car(LocalDateTime.parse(str, formatter), "4WD", 10000);
        Body body = new Body("sedan", "white", "left");
        Engine engine = new Engine("disel", 3.0, 200, 100);
        GearBox gearBox = new GearBox("robot", 6);
        Manufacturer manufacturer = new Manufacturer("Lada", "Russia");
        Model model = new Model("Kalina", LocalDateTime.parse(str, formatter), true);
        car.setModel(model);
        car.setEngine(engine);
        car.setGearbox(gearBox);
        car.setBody(body);
        car.setManufacturer(manufacturer);
        User user = new User("test", "test", "test");
        Item item = new Item("Kalina, 2010", "Want to sell test car");
        item.setAuthor(user);
        item.setCar(car);

        Item result = itemRepository.save(item);

        assertThat(result, is(item));
        assertNotEquals(((List<Item>) itemRepository.findAll()).size(), 0);
//        assertEquals((itemRepository.findByCarPhotoNotNull()).size(), 0);
        assertNotEquals((itemRepository.findByCarManufacturerName("Lada")).size(), 0);
        assertNotEquals((itemRepository.findByCreatedGreaterThanEqual(LocalDateTime.now().minusDays(1L))).size(), 0);
    }

    @Test
    public void whenUserCreateThenFind() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDataConfig.class);
        UserDataRepository repository = context.getBean(UserDataRepository.class);
        User user = new User("user", "user", "user");
        User result = repository.save(user);
        assertThat(result, is(user));
    }

    @Test
    public void whenWrongCredentialsThenEmptyOptional() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDataConfig.class);
        UserDataRepository repository = context.getBean(UserDataRepository.class);
        Optional<User> result = repository.findByLoginAndPassword("1user", "1user");
        assertThat(result.isPresent(), is(false));
    }
}