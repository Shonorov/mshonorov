package ru.job4j.users;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Persistent layout for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MemoryStore implements Store {
    /**
     * Memory store singleton.
     */
    private static final MemoryStore INSTANCE = new MemoryStore();

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    /**
     * List of users.
     */
    private List<User> users = new CopyOnWriteArrayList<User>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void update(User user, User update) {
        users.set(users.indexOf(user), update);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<User> result =  Optional.empty();
        for (User user : users) {
            if (id.equals(user.getId())) {
                result = Optional.of(user);
            }
        }
        return result;
    }
}
