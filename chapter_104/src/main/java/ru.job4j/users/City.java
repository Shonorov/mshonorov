package ru.job4j.users;

import java.util.Objects;

/**
 * User role model for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class City {

    private String id;
    private String name;
    private String countryID;

    public City(String id, String name, String countryID) {
        this.id = id;
        this.name = name;
        this.countryID = countryID;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryID() {
        return countryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return Objects.equals(id, city.id)
                && Objects.equals(name, city.name)
                && Objects.equals(countryID, city.countryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countryID);
    }

    @Override
    public String toString() {
        return "City{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", countryID='" + countryID + '\''
                + '}';
    }
}
