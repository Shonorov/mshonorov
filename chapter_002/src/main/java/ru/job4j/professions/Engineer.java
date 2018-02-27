package ru.job4j.professions;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Engineer extends Profession {

    public Engineer(String name) {
        super(name, "Инженер");
    }

    public String build(House house) {
        return this.getProfession() + " " + this.getName() + " строит дом по адресу: " + house.getAddress();
    }
}
