package ru.job4j.cars.model;

import java.time.LocalDateTime;

/**
 * Car model for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Model {

    private Integer id;
    private String name;
    private LocalDateTime releaseDate;
    private Boolean manufacturing;

    public Model() {
    }

    public Model(Integer id) {
        this.id = id;
    }

    public Model(String name, LocalDateTime releaseDate, Boolean manufacturing) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.manufacturing = manufacturing;
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

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean getManufacturing() {
        return manufacturing;
    }

    public void setManufacturing(boolean manufacturing) {
        this.manufacturing = manufacturing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Model)) {
            return false;
        }

        Model model = (Model) o;

        if (!name.equals(model.name)) {
            return false;
        }
        if (!releaseDate.equals(model.releaseDate)) {
            return false;
        }
        return manufacturing.equals(model.manufacturing);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + manufacturing.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Model{" + "id=" + id
                + ", name='" + name
                + ", releaseDate=" + releaseDate
                + ", manufacturing=" + manufacturing + '}';
    }
}
