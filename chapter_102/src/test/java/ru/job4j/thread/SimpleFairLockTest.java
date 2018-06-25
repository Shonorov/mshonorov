package ru.job4j.thread;

import org.junit.Test;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleFairLockTest {

    @Test
    public void whenTwoThreadsThenDeadlock() {
        SimpleFairLock lock1 = new SimpleFairLock();
        SimpleFairLock lock2 = new SimpleFairLock();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleFairLock.action(lock1, lock2);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleFairLock.action(lock2, lock1);
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
