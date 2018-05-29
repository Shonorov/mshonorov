package ru.job4j.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Logic layout for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService {
    /**
     * ValidateService singleton instance.
     * MemoryStore instance.
     */
    private static final ValidateService INSTANCE = new ValidateService();
    private final Store store = MemoryStore.getInstance();

    public static ValidateService getInstance() {
        return INSTANCE;
    }

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
    public boolean update(User user, String newname, String newlogin, String newemail, String newpassword, String newrole) {
        boolean result = false;
        User update = new User();
        if (store.findAll().contains(user)) {
            if (newname != null) {
                update.setName(newname);
            } else {
                update.setName(user.getName());
            }
            if (newlogin != null) {
                update.setLogin(newlogin);
            } else {
                update.setLogin(user.getLogin());
            }
            if (newemail != null) {
                update.setEmail(newemail);
            } else {
                update.setEmail(user.getEmail());
            }
            if (newpassword != null) {
                update.setPassword(newpassword);
            } else {
                update.setPassword(user.getPassword());
            }
            if (newrole != null) {
                update.setRole(newrole);
            } else {
                update.setRole(user.getRole());
            }
            if (!store.findAll().contains(update)) {
                store.update(user, update);
                result = true;
            }
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

    /**
     * Find User by login.
     * @param login to find.
     * @return Optional of user.
     */
    public Optional<User> findByLogin(String login) {
        return store.findByLogin(login);
    }

    /**
     * Get roles list.
     * @return list of all roles.
     */
    public List<String> findRoles() {
        List<String> result = new ArrayList<>();
        for (Role role : store.getRoles()) {
            result.add(role.getRole());
        }
        return result;
    }

    /**
     * Find user with same credentials.
     * @param login to check.
     * @param password to check.
     * @return optional of user.
     */
    public Optional<User> authenticate(String login, String password) {
        return store.checkCredentials(login, password);
    }
}
