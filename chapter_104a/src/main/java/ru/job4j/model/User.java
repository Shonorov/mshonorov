package ru.job4j.model;

import java.time.LocalDateTime;

/**
 * User model for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class User {

    private String id;
    private String name;
    private String login;
    private String password;
    private String address;
    private String styles;
    private String role;

    public User(String id, String name, String login, String password, String address, String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.address = address;
        this.styles = "";
        this.role = role;
    }

    public User(String id, String name, String login, String password, String address, String styles, String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.address = address;
        this.styles = styles;
        this.role = role;
    }

    public User(String name, String login, String password, String address, String role) {
        this.id = LocalDateTime.now().toString() + (short) (Math.random() * 100);
        this.name = name;
        this.login = login;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public String getStyles() {
        return styles;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (!name.equals(user.name)) {
            return false;
        }
        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", address='" + address + '\''
                + ", styles='" + styles + '\''
                + ", role='" + role + '\'' + '}';
    }
}
