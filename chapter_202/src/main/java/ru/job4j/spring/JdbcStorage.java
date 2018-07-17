package ru.job4j.spring;

import org.springframework.stereotype.Component;

/**
 * Database storage realization for Spring application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Component
public class JdbcStorage implements Storage {

    @Override
    public void add(User user) {
        System.out.println("Store " + user.getName() + " to database!");
    }
}
