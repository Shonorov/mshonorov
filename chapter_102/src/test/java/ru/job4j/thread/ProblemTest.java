package ru.job4j.thread;
import org.junit.Test;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ProblemTest {
    /**
     * Sometimes different threads print the same value.
     */
    @Test
    public void whenDifferentThreadsHaveSameValue() {
        Problem problem = new Problem();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                problem.increment(10);
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                problem.increment(10);
            }
        };
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Value printed not all of the run times.
     */
    @Test
    public void whenIfHappensBeforeSetThenNoOutput() {
        Problem problem = new Problem();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                problem.setValue(100);
                problem.setReady(true);
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                problem.ifTrue();
            }
        };
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
