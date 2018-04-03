package ru.job4j.nonblock;
/**
 * Simple non-blocking cache element.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Model {
    private volatile int version;
    private String name;

    public Model(String name) {
        this.name = name;
        this.version = 1;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public synchronized void addVersion() {
        this.version++;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
