package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple limited blocking queue realization.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * Blocking queue.
     * Queue max size.
     * Multithreading monitor.
     * Blocked true if queue is full.
     */
    @GuardedBy("monitor")
    private Queue<T> queue = new LinkedList<>();
    private static final int MAX_SIZE = 2;
    private final Object monitor = new Object();

    /**
     * Offer value to queue.
     * @param value value to offer.
     */
    public void offer(T value) {
        synchronized (this.monitor) {
            while (queue.size() == MAX_SIZE) {
                System.out.println("Full");
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(value);
            monitor.notify();
            System.out.println("Add " + value);
        }
    }

    /**
     * Poll value from queue.
     * @return value.
     */
    public T poll() {
        synchronized (this.monitor) {
            while (queue.size() == 0) {
                System.out.println("Empty");
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Poll " + queue.peek());
            monitor.notify();
            return queue.poll();
        }
    }
}
