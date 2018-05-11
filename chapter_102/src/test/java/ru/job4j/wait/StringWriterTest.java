package ru.job4j.wait;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;

import java.util.concurrent.locks.ReentrantLock;

public class StringWriterTest {

    @Test
    public void whenAddThenReturnString() {
        ReentrantLock lock = new ReentrantLock();
        Thread thread1 = new Thread(new StringWriter(lock, 1));
        Thread thread2 = new Thread(new StringWriter(lock, 2));
        try {
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String expect1 = "111111111122222222221111111111222222222211111111112222222222";
        String expect2 = "222222222211111111112222222222111111111122222222221111111111";
        assertThat(StringWriter.content, isOneOf(expect1, expect2));
    }
}
