package ru.job4j.users;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Logic layout for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService {

    private static final ValidateService instance = new ValidateService();

    public static ValidateService getInstance() {
        return instance;
    }

    private final Store store = MemoryStore.getInstance();

    //TODO Класс ValidateService сделать синглетоном. Использовать Eager initiazitation.

    public void add(User user) {

    }

    public void update(User user, User update) {

    }

    public List<User> findAll() {
        return new CopyOnWriteArrayList<User>();
    }

    public void findById(String id) {

    }
}
