package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;

/**
 * Simple limited blocking queue realization.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class ThreadPool {

    /**
     * List of worker threads.
     * Max threads possible.
     * Thread pool monitor object.
     */
    @GuardedBy("this")
    private LinkedList<Work> works = new LinkedList<>();
    private static final int MAX_SIZE = Runtime.getRuntime().availableProcessors();
    private final Object monitor = new Object();

    public int getThreads() {
        return MAX_SIZE;
    }

    /**
     * Add thread to worker pool if processor available.
     * @param work work to add.
     */
    public void add(Work work) {
        synchronized (this.monitor) {
            while (works.size() == MAX_SIZE) {
                try {
                    System.out.println("Overflow!");
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            works.add(work);
            new Thread(work).start();
            monitor.notify();
        }
    }

    /**
     * Remove worker from thread pool.
     */
    public void remove() {
        synchronized (this.monitor) {
            while (works.size() == 0) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (works.size() == MAX_SIZE) {
                monitor.notify();
            }
            Work current = works.poll();
            System.out.println("Thread removed: " + current.getWorkerName());
        }
    }
}
