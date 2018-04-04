package ru.job4j.wait;

import org.junit.Test;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleBlockingQueueTest {

    @Test
    public void whenBlockingQueueTest() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                queue.offer(1);
                queue.offer(2);
                queue.offer(3);
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                queue.poll();
                queue.poll();
                queue.poll();
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
