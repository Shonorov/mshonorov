package ru.job4j.wait;
/**
 * Work thread for thread pool.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Work implements Runnable {
    private String name;

    public Work(String name) {
        this.name = name;
    }

    public String getWorkerName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println("Thread started: " + this.name);
    }
}
