package ru.job4j.wait;
import org.junit.Test;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleLockTest {

    class CustomLockExample implements Runnable {
        private SimpleLock lock = new SimpleLock();
        @Override
        public void run() {
            try {
                if (lock.lock()) {
                    Thread.sleep(500);
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " lock interrupted.");
            }
        }
    }

    @Test
    public void whenLockedThenInterruptThread() throws InterruptedException {
        CustomLockExample example = new CustomLockExample();
        Thread thread1 = new Thread(example);
        Thread thread2 = new Thread(example);
        Thread thread3 = new Thread(example);
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
    }
}
