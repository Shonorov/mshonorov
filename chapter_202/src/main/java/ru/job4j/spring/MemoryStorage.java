package ru.job4j.spring;

/**
 * Memory storage realization for Spring application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class MemoryStorage implements Storage {

    @Override
    public void add(User user) {
        System.out.println("Store " + user.getName() + " to memory!");
    }
}
