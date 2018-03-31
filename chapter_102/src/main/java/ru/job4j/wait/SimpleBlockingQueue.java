package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Optional;
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
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int size;
    private final Object monitor = new Object();
    private boolean blocked = false;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    /**
     * Offer value to queue.
     * @param value value to offer.
     */
    public void offer(T value) {
        synchronized (this.monitor) {
            if (queue.size() == size) {
                System.out.println("Full " + value);
                switchBlock(true);
            }
            while (blocked) {
                try {
                    System.out.println("Blocked write");
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (queue.size() < size) {
                queue.add(value);
                monitor.notify();
                System.out.println("Add " + value);
            }
        }
    }

    /**
     * Poll value from queue.
     * @return value.
     */
    public T poll() {
        Optional<T> result = Optional.empty();
        synchronized (this.monitor) {
            if (queue.size() == 0) {
                try {
                    System.out.println("Empty");
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (queue.size() != 0) {
                result = Optional.of(queue.poll());
                switchBlock(false);
                System.out.println("Poll " + result.get());
            }
        }
        return result.get();
    }

    private void switchBlock(boolean value) {
        synchronized (this.monitor) {
            this.blocked = value;
            this.monitor.notify();
        }
    }
}
