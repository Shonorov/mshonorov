package ru.job4j.thread;
/**
 * Calculates execution time.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Time implements Runnable {

    private long waitTime;

    public Time(long waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stop!");
    }
}
