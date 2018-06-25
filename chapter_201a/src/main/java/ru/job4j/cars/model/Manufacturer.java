package ru.job4j.cars.model;

import java.util.List;
import javax.persistence.*;

/**
 * Car manufacturer model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */

@Entity
@Table (name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "id")
    private Integer id;

    @Column (name = "name")
    private String name;

    @Column (name = "country")
    private String country;

    @Column (name = "models")
    @OneToMany
    private List<Model> models;

    public Manufacturer() {
    }

    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Manufacturer)) {
            return false;
        }

        Manufacturer that = (Manufacturer) o;

        if (!name.equals(that.name)) {
            return false;
        }
        return country.equals(that.country);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Manufacturer{" + "id=" + id
                + ", name='" + name
                + ", country='" + country
                + ", models=" + models + '}';
    }
}
