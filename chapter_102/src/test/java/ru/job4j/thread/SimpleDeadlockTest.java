package ru.job4j.thread;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleDeadlockTest {

    @Test //(expected = StackOverflowError.class)
    public void whenTwoThreadsThenDadlock() {
        SimpleDeadlock deadlock = new SimpleDeadlock();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    deadlock.threadA();
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    deadlock.threadB();
                }
            }
        });
        threadA.start();
        threadB.start();
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(deadlock.getSum(), is(0));
    }
}
