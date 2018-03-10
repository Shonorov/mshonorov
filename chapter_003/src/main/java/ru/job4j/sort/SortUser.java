package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * User class to sort.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SortUser {

    /**
     * Sorts array of users by name and age.
     * @param users array of users.
     * @return sorted TreeSet.
     */
    public Set<User> sort(List<User> users) {
        Set<User> result = new TreeSet<>();
        result.addAll(users);
        return result;
    }
}
