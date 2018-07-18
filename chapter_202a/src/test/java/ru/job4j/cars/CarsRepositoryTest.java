package ru.job4j.cars;

import org.junit.Test;
import ru.job4j.cars.model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarsRepositoryTest {

    @Test
    public void whenCreateAllEntitiesThenFind() {
        String str = "01-01-2010 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        CarsRepository repository = new CarsRepository();
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

        String id = repository.createItem(item).toString();
        Item result = repository.findItemById(id).get();

        assertThat(result, is(item));
        assertNotEquals(repository.getAllItems().size(), 0);
        assertNotEquals(repository.getAllItemsByPhoto(false).size(), 0);
        assertNotEquals(repository.getItemsByManufacturer("Lada").size(), 0);
    }

    @Test
    public void whenUserCreateThenFind() {
        CarsRepository repository = new CarsRepository();
        User user = new User("user", "user", "user");
        Integer id = (Integer) repository.createUser(user);
        assertThat(repository.findUserByID(id.toString()).get(), is(user));
    }

    @Test
    public void whenWrongCredentialsThenEmptyOptional() {
        CarsRepository repository = new CarsRepository();
        Optional<User> result = repository.authenticate("1user", "1user");
        assertThat(result.isPresent(), is(false));
    }

}