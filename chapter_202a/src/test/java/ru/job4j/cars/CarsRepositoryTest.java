package ru.job4j.cars;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.job4j.cars.config.HibernateConfig;
import ru.job4j.cars.config.SpringDataConfig;
import ru.job4j.cars.dao.CarsRepository;
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
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
//        CarsRepository repository = context.getBean(CarsRepository.class);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDataConfig.class);
        ItemDataRepository itemRepository = context.getBean(ItemDataRepository.class);
        UserDataRepository userRepository = context.getBean(UserDataRepository.class);
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


//        User user = new User("test", "test", "test");
        User user = userRepository.findById(1).get();
        Item item = new Item("Kalina, 2010", "Want to sell test car");
        item.setAuthor(user);
        item.setCar(car);
        System.out.println(item);
        itemRepository.save(item);


//        Item result = itemRepository.findById().get();
//
//        assertThat(result, is(item));
//        assertNotEquals(repository.getAllItems().size(), 0);
//        assertNotEquals(repository.getAllItemsByPhoto(false).size(), 0);
//        assertNotEquals(repository.getItemsByManufacturer("Lada").size(), 0);
    }

//    @Test
//    public void whenUserCreateThenFind() {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
//        CarsRepository repository = context.getBean(CarsRepository.class);
//        User user = new User("user", "user", "user");
//        Integer id = (Integer) repository.createUser(user);
//        assertThat(repository.findUserByID(id.toString()).get(), is(user));
//    }
//
//    @Test
//    public void whenWrongCredentialsThenEmptyOptional() {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
//        CarsRepository repository = context.getBean(CarsRepository.class);
//        Optional<User> result = repository.authenticate("1user", "1user");
//        assertThat(result.isPresent(), is(false));
//    }

    @Test
    public void whenSpringDataThenFindAll() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringDataConfig.class);
        ItemDataRepository itemRepository = context.getBean(ItemDataRepository.class);

//        Optional<User> result = repository.authenticate("1user", "1user");
//        for (Object o : repository.findByCarPhotoNotNull()) {
//            System.out.println(o);
//        }
        List<Item> result = (List<Item>) itemRepository.findAll();
        System.out.println(result.size());
        System.out.println(itemRepository.findByCarPhotoNotNull().size());
        System.out.println(itemRepository.findByCarManufacturerName("Lada").size());
        System.out.println(itemRepository.findByCreatedGreaterThanEqual(LocalDateTime.now().minusDays(1L)).size());

//        assertThat(result.isPresent(), is(false));
    }
}