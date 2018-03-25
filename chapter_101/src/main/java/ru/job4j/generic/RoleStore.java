package ru.job4j.generic;
/**
 * Stores Role objects.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class RoleStore extends AbstractStore<Role> {

    public RoleStore(SimpleArray<Role> content) {
        super(content);
    }
}
