package ru.job4j.spring;

import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Database storage realization for Spring application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Component
public class JdbcStorage implements Storage {

    private JdbcDao dao = JdbcDao.getInstance();

    @Override
    public void add(User user) {
        System.out.println("Store " + user.getName() + " to database!");
        dao.add(user);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }
}
