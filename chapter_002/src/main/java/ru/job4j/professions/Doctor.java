package ru.job4j.professions;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Doctor extends Profession {

    public Doctor(String name) {
        super(name, "Доктор");
    }

    public String heal(Patient patient) {
        return this.getProfession() + " " + this.getName() + " лечит пациента " + patient.getName() + " от болезни " + patient.getDiagnose();
    }
}
