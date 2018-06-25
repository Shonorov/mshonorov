package ru.job4j.cars.model;

import javax.persistence.*;
/**
 * Engine model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */

@Entity
@Table (name = "engine")
public class Engine {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    private Integer id;

    @Column (name = "type")
    private String type;

    @Column (name = "volume")
    private Double volume;

    @Column (name = "power")
    private Integer power;

    @Column (name = "mileage")
    private Integer mileage;

    public Engine() {
    }

    public Engine(String type, Double volume, Integer power, Integer mileage) {
        this.type = type;
        this.volume = volume;
        this.power = power;
        this.mileage = mileage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Engine)) {
            return false;
        }

        Engine engine = (Engine) o;

        if (!type.equals(engine.type)) {
            return false;
        }
        if (!volume.equals(engine.volume)) {
            return false;
        }
        if (!power.equals(engine.power)) {
            return false;
        }
        return mileage.equals(engine.mileage);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + volume.hashCode();
        result = 31 * result + power.hashCode();
        result = 31 * result + mileage.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Engine{" + "id=" + id
                + ", type='" + type
                + ", volume=" + volume
                + ", power=" + power
                + ", mileage=" + mileage + '}';
    }
}
