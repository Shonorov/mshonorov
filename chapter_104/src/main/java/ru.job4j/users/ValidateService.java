package ru.job4j.users;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Logic layout for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService {
    /**
     * ValidateService singleton instance.
     */
    private static final ValidateService INSTANCE = new ValidateService();

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    private final Store store = MemoryStore.getInstance();

    /**
     * Check if user exists and add it.
     * @param user to check.
     * @return true if success.
     */
    public boolean add(User user) {
        boolean result = false;
        if (!store.findAll().contains(user)) {
            store.add(user);
            result = true;
        }
        return result;
    }

    /**
     * Check user and new parameters and update user.
     * @param user to check.
     * @param newname name to change.
     * @param newlogin login to change.
     * @param newemail email to change.
     * @return true if success.
     */
    public boolean update(User user, String newname, String newlogin, String newemail) {
        boolean result = false;
        User update = new User();
        if (store.findAll().contains(user)) {
            if (newname != null) {
                update.setName(newname);
            } else {
                update.setName(user.getName());
            }
            if (newlogin != null) {
                update.setName(newlogin);
            } else {
                update.setLogin(user.getLogin());
            }
            if (newemail != null) {
                update.setName(newemail);
            } else {
                update.setEmail(user.getEmail());
            }
            store.update(user, update);
            result = true;
        }
        return result;
    }

    /**
     * Check if user exists and delete it.
     * @param user to check.
     * @return true if success.
     */
    public boolean delete(User user) {
        boolean result = false;
        if (store.findAll().contains(user)) {
            store.delete(user);
            result = true;
        }
        return result;
    }

    /**
     * Find all users.
     * @return User list.
     */
    public List<User> findAll() {
        return store.findAll();
    }

    /**
     * Find User by id..
     * @param id to find.
     * @return Optional of user.
     */
    public Optional<User> findById(String id) {
        return store.findById(id);
    }
}
