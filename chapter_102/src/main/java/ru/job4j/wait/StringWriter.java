package ru.job4j.wait;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Simple multithreaded string digit appender.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class StringWriter implements Runnable {

    /**
     * Common string content.
     * Class lock.
     * Digit to append.
     * Append repeat count.
     */
    public static String content = "";
    private final ReentrantLock lock;
    private int digit;
    private static final int REPEAT = 3;

    public StringWriter(ReentrantLock lock, int digit) {
        this.lock = lock;
        this.digit = digit;
    }

    @Override
    public void run() {
        for (int i = 0; i < REPEAT; i++) {
            synchronized (lock) {
                append(digit);
                try {
                    lock.notify();
                    lock.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Append ten digits.
     * @param digit digit to append.
     */
    private synchronized void append(int digit) {
        String current = String.valueOf(digit);
        StringBuilder builder = new StringBuilder(content);
        for (int i = 0; i < 10; i++) {
            builder.append(current);
        }
        content = builder.toString();
    }
}
