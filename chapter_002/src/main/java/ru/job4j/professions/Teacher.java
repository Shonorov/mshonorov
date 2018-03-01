package ru.job4j.professions;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Teacher extends Profession {

    public Teacher(String name) {
        super(name, "Учитель");
    }

    public String teach(Student student) {
        return this.getProfession() + " " + this.getName() + " учит студента " + student.getName();
    }
}
