package ru.job4j.spring;

import java.util.List;

/**
 * Storage model for Spring application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Storage {

    /**
     * Add user to storage.
     * @param user user to add.
     */
    void add(User user);

    /**
     * Get all users from storage.
     * @return list of all users.
     */
    List<User> getAll();
}
