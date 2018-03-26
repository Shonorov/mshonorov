package ru.job4j.thread;
/**
 * Calculates execution time.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Time implements Runnable {

    private long waitTime;
    Thread monitor;

    public Time(long waitTime, Thread monitor) {
        this.waitTime = waitTime;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        monitor.interrupt();
        System.out.println("Stop!");
    }
}
