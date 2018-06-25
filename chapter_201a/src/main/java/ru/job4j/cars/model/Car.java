package ru.job4j.cars.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import javax.persistence.*;

/**
 * Car model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Entity
@Table (name = "cars")
public class Car {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    private Integer id;

    @Column (name = "manufactured")
    private LocalDateTime manufactured;

    @Column (name = "drive")
    private String drive;

    @Column (name = "price")
    private Integer price;

    @Column (name = "photo")
    private byte[] photo;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "manufacturer_id", foreignKey = @ForeignKey(name = "Manufacturer_id_FK"))
    private Manufacturer manufacturer;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "model_id", foreignKey = @ForeignKey(name = "Model_id_FK"))
    private Model model;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "engine_id", foreignKey = @ForeignKey(name = "Engine_id_FK"))
    private Engine engine;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "body_id", foreignKey = @ForeignKey(name = "Body_id_FK"))
    private Body body;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "gearbox_id", foreignKey = @ForeignKey(name = "Gearbox_id_FK"))
    private GearBox gearbox;

    public Car() {
    }

    public Car(Integer id) {
        this.id = id;
    }

    public Car(LocalDateTime manufactured, String drive, Integer price) {
        this.manufactured = manufactured;
        this.drive = drive;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getManufactured() {
        return manufactured;
    }

    public void setManufactured(LocalDateTime manufactured) {
        this.manufactured = manufactured;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public GearBox getGearbox() {
        return gearbox;
    }

    public void setGearbox(GearBox gearbox) {
        this.gearbox = gearbox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }

        Car car = (Car) o;

        if (!manufactured.equals(car.manufactured)) {
            return false;
        }
        if (!drive.equals(car.drive)) {
            return false;
        }
        if (!price.equals(car.price)) {
            return false;
        }
        if (!Arrays.equals(photo, car.photo)) {
            return false;
        }
        if (!manufacturer.equals(car.manufacturer)) {
            return false;
        }
        if (!model.equals(car.model)) {
            return false;
        }
        if (!engine.equals(car.engine)) {
            return false;
        }
        if (!body.equals(car.body)) {
            return false;
        }
        return gearbox.equals(car.gearbox);
    }

    @Override
    public int hashCode() {
        int result = manufactured.hashCode();
        result = 31 * result + drive.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + Arrays.hashCode(photo);
        result = 31 * result + manufacturer.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + engine.hashCode();
        result = 31 * result + body.hashCode();
        result = 31 * result + gearbox.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id
                + ", manufactured=" + manufactured
                + ", drive='" + drive
                + ", price=" + price
                + ", photo=" + Arrays.toString(photo)
                + ", manufacturer=" + manufacturer
                + ", model=" + model
                + ", engine=" + engine
                + ", body=" + body
                + ", gearbox=" + gearbox + '}';
    }
}
