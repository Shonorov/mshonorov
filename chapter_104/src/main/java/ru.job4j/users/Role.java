package ru.job4j.users;
/**
 * User role model for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Role {
    /**
     * Role name.
     * Administrator rights.
     */
    private String role;
    private boolean administrator;

    public Role(String role, boolean administrator) {
        this.role = role;
        this.administrator = administrator;
    }

    public String getRole() {
        return role;
    }

    public boolean isAdministrator() {
        return administrator;
    }
}
