package ru.job4j.thread;
/**
 * Multithreading problem example.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Problem {
    private int count = 0;
    private boolean ready;
    private int value;

    public void increment(int times) {
        for (int i = 0; i < times; i++) {
            this.count++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + this.count);
        }
    }

    public void ifTrue() {
        if (ready) {
            System.out.println(value);
        }
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
