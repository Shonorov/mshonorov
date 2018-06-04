package ru.job4j.users;
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
}
