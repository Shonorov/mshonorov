package ru.job4j.cars;

import org.junit.Test;
import ru.job4j.cars.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarsRepositoryTest {

    @Test
    public void whenCreateCarThenCreated() {
        String str = "01-01-2010 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        CarsRepository repository = new CarsRepository();
        Car car = new Car(LocalDateTime.parse(str, formatter), "4WD", 10000);
        Body body = new Body("sedan", "white", "left");
        Engine engine = new Engine("disel", 3.0, 200, 100);
        GearBox gearBox = new GearBox("robot", 6);
        Manufacturer manufacturer = new Manufacturer("Lada", "Russia");
        List<Model> models = new ArrayList<>();
        Model model = new Model("Kalina", LocalDateTime.parse(str, formatter), true);
        models.add(model);
        manufacturer.setModels(models);
        repository.createManufacturer(manufacturer);
        car.setModel(model);
        car.setEngine(engine);
        car.setGearbox(gearBox);
        car.setBody(body);
        car.setManufacturer(manufacturer);

        String id = repository.createCar(car).toString();
        assertThat(repository.findCarById(id).get(), is(car));
    }

    @Test
    public void whenCreateManufacturerThenModelSaved() {
        String str = "01-01-2010 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        CarsRepository repository = new CarsRepository();
        Manufacturer manufacturer = new Manufacturer("Lada", "Russia");
        List<Model> models = new ArrayList<>();
        Model model = new Model("Kalina", LocalDateTime.parse(str, formatter), true);
        models.add(model);
        manufacturer.setModels(models);
        repository.createManufacturer(manufacturer);
    }
}