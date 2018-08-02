package ru.job4j.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Fair lock realization (no wait function allowed).
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleFairLock {

    private int amount;
    private final Lock lock = new ReentrantLock(true);

    private void plus() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        amount += 1;
    }

    private void minus() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        amount -= 1;
    }

    public static void action(SimpleFairLock from, SimpleFairLock to) {
        from.lock.lock();
        from.minus();
        try {
            if (to.lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                to.plus();
                to.lock.unlock();
            } else {
                System.out.println(Thread.currentThread().getName() + " Deadlock!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        from.lock.unlock();
    }

}
