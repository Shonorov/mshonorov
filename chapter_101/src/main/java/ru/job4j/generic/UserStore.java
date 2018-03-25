package ru.job4j.generic;
/**
 * Stores User objects.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserStore extends AbstractStore<User> {

    public UserStore(SimpleArray<User> content) {
        super(content);
    }
}
