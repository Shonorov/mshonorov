package ru.job4j.professions;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Patient {
    private String name;
    private Diagnose diagnose;

    public Patient(String name, String diagnose) {
        this.name = name;
        this.diagnose = new Diagnose(diagnose);
    }

    public String getName() {
        return name;
    }

    public String getDiagnose() {
        return this.diagnose.getName();
    }
}
