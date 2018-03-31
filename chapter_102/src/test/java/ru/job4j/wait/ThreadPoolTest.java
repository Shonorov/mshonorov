package ru.job4j.wait;
import org.junit.Test;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ThreadPoolTest {

    @Test
    public void whenNoThreadsThenWait() throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        Thread worker = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < pool.getThreads() * 2; i++) {
                    pool.add(new Work(String.valueOf(i)));
                }
            }
        });

        Thread releaser = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < pool.getThreads() * 2; i++) {
                    pool.remove();
                }
            }
        });
        worker.start();
        releaser.start();
        worker.join();
        releaser.join();

    }
}
