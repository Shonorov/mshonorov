package ru.job4j.thread;
/**
 * Deadlock realization without wait function usage.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleDeadlock {

    private int sum = 0;
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public int threadA() {
        int result = sum;
        try {
            if (!Thread.currentThread().isInterrupted()) {
                synchronized (lock1) {
                    synchronized (lock2) {
                        result += threadB();
                    }
                }
            }
        } catch (StackOverflowError e) {
            System.out.println(Thread.currentThread().getName() + " deadlock!");
            Thread.currentThread().interrupt();
        }
        return result;
    }

    public int threadB() {
        int result = sum;
        try {
            if (!Thread.currentThread().isInterrupted()) {
                synchronized (lock2) {
                    synchronized (lock1) {
                        result += threadA();
                    }
                }
            }
        } catch (StackOverflowError e) {
            System.out.println(Thread.currentThread().getName() + " deadlock!");
            Thread.currentThread().interrupt();
        }
        return result;
    }

    public int getSum() {
        return sum;
    }
}
