package ru.job4j.wait;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Simple multithreaded string writer.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class StringWriter implements Runnable {

    @GuardedBy("this")
    private static final String content = "";
    private ReentrantLock lock;
    private String digit;

    public StringWriter(ReentrantLock lock, int digit) {
        this.lock = lock;
        this.digit = String.valueOf(digit);
    }

    @Override
    public void run() {
        //https://stackoverflow.com/questions/15530484/how-to-switch-between-two-thread-back-and-forth
    }

    public synchronized void append(int digit) {
        System.out.println(Thread.currentThread().getName() + " append");

    }

    public synchronized String getContent() {
        return this.content;
    }
}
