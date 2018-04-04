package ru.job4j.synchronize;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Threadsafe User store.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private ArrayList<User> users = new ArrayList<>();

    /**
     * Add user to the store.
     * @param user to add.
     * @return true if success.
     */
    public synchronized boolean add(User user) {
        return users.add(user);
    }

    /**
     * Update user.
     * @param user to update.
     * @return true if success.
     */
    public synchronized boolean update(User user) {
        User current = findByID(user.getId());
        current.addAmount(user.getAmount());
        return true;
    }

    /**
     * Delete User from store.
     * @param user User to delete.
     * @return true if success.
     */
    public synchronized boolean delete(User user) {
        return users.remove(user);
    }

    /**
     * Transfer money from one user to another.
     * @param fromId user id to transfer from.
     * @param toId user id to transfer to.
     * @param amount amount to transfer.
     * @return true if success.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User from = findByID(fromId);
        User to = findByID(toId);
        if (from.getAmount() >= amount) {
            from.addAmount(-amount);
            to.addAmount(amount);
            result = true;
        }
        return result;
    }

    /**
     * Find user by ID.
     * @param id to find.
     * @return User.
     */
    public synchronized User findByID(int id) {
        Optional<User> result = Optional.empty();
        for (User current : users) {
            if (current.getId() == id) {
                result = Optional.of(current);
            }
        }
        if (!result.isPresent()) {
            throw new NoSuchElementException();
        }
        return result.get();
    }
}
