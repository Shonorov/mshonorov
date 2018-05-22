package ru.job4j.users;

import java.util.ArrayList;
import java.util.List;

/**
 * User role list for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Roles {

    private List<Role> roles = createRoles();

    private List<Role> createRoles() {
        List<Role> result = new ArrayList<>();
        result.add(new Role("Administrator", true));
        result.add(new Role("User", false));
        return result;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
