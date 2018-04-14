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
     * @return true if success.
     */
    public synchronized boolean lock() {
        boolean result = false;
        if (unlocked && owner == null) {
            owner = Thread.currentThread();
            unlocked = false;
            result = true;
            System.out.println(Thread.currentThread().getName() + " locked.");
        } else {
            System.out.println(Thread.currentThread().getName() + " lock failed.");
        }
        return result;
    }

    /**
     * Disable lock and clear owner thread.
     */
    public synchronized void unlock() {
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
