package ru.job4j.wait;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class StringWriterTest {
    @Test
    public void whenAddThenReturnString() {

        ReentrantLock lock = new ReentrantLock();
        Thread thread1 = new Thread(new StringWriter(lock, 1));
        Thread thread2 = new Thread(new StringWriter(lock, 2));
        thread1.start();
        thread2.start();
//         try {
//             thread1.join();
//             thread2.join();
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }

    }
}
