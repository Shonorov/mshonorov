package ru.job4j.thread;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CDLDeadlockTest {

    @Test
    public void whenTwoThreadsThenDeadlock() {
        CDLDeadlock deadlock = new CDLDeadlock();
        CountDownLatch c = new CountDownLatch(1);
        ArrayBlockingQueue b = new ArrayBlockingQueue(1);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                deadlock.doPut(c, b);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                deadlock.doTake(c, b);
            }
        });
        thread1.start();
        thread2.start();

//        Remove comment for deadlock!
//        try {
//            thread1.join();
//            thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}