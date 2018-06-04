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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }

        Role role1 = (Role) o;

        if (administrator != role1.administrator) {
            return false;
        }
        return role != null ? role.equals(role1.role) : role1.role == null;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (administrator ? 1 : 0);
        return result;
    }
}
