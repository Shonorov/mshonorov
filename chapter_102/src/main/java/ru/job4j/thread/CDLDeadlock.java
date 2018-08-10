package ru.job4j.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * CountdownLatch deadlock without sleep method.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CDLDeadlock {

    public void doPut(CountDownLatch c, ArrayBlockingQueue b) {
        try {
            b.put(1);
            b.put(2);
            c.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doTake(CountDownLatch c, ArrayBlockingQueue b) {
        try {
            c.await();
            b.take();
            b.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
