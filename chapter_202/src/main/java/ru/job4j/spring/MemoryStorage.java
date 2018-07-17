package ru.job4j.spring;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Memory storage realization for Spring application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MemoryStorage implements Storage {

    private List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        System.out.println("Store " + user.getName() + " to memory!");
        users.add(user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}
