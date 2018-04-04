package ru.job4j.synchronize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;

import org.junit.Test;

import java.util.NoSuchElementException;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserStorageTest {

    @Test
    public void whenSynchronizedThenAddAndTransfer() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.transfer(1, 2, 50);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.transfer(2, 1, 100);
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(storage.findByID(1).getAmount(), is(150));
        assertThat(storage.findByID(2).getAmount(), is(150));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSynchronizedThenAddUpdateAndDelete() {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.update(new User(1, 200));
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                storage.update(new User(1, 300));
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(storage.findByID(1).getAmount(), is(600));
        assertThat(storage.delete(storage.findByID(1)), is(true));
        storage.delete(storage.findByID(1));
    }
}
