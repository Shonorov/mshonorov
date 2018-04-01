package ru.job4j.wait;
/**
 * Simple lock realization.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleLock {
    /**
     * Thread owner of lock.
     * Lock state.
     */
    private Thread owner = null;
    private volatile boolean unlocked = true;

    /**
     * Enable lock and set owner thread.
     */
    public void lock() {
        if (unlocked && owner == null) {
            owner = Thread.currentThread();
            unlocked = false;
            System.out.println(Thread.currentThread().getName() + " locked.");
        } else {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Disable lock and clear owner thread.
     */
    public void unlock() {
        if (!unlocked && owner == Thread.currentThread()) {
            owner = null;
            unlocked = true;
            System.out.println(Thread.currentThread().getName() + " unlocked.");
        } else {
            System.out.println(Thread.currentThread().getName() + " unlock failed.");
            throw new IllegalMonitorStateException();
        }
    }
}
