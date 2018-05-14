package ru.job4j.users;

import java.util.List;

/**
 * Persistent layout interface for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Store {

    void add(User user);
    void update(User user, User update);
    void delete(User user);
    List<User> findAll();
    User findById(String id);
}
