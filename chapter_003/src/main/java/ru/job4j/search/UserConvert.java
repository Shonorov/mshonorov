package ru.job4j.search;

import java.util.HashMap;
import java.util.List;
/**
 * User class converter.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserConvert {
    /**
     * Convert list of users to map with its IDs as keys.
     * @param list list of Users.
     * @return map with ids and Users.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
