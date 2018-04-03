package ru.job4j.nonblock;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.notNullValue;

import java.util.NoSuchElementException;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleCacheTest {

    @Test (expected = RuntimeException.class)
    public void whenMultipleThreadsModifyThenException() throws InterruptedException {
        SimpleCache cache = new SimpleCache();
        Model first = new Model("model");
        cache.add(first);
        Model second = new Model("model");
        second.addVersion();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.update(second);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.update(second);
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(cache.getModels().size(), is(notNullValue()));
//        System.out.println(cache.getModels().values());
    }
}
