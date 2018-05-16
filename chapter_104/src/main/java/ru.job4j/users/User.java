package ru.job4j.users;

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
    private String email;
    private LocalDateTime createDate;

    public User() {
        this.createDate = LocalDateTime.now();
        this.id = createDate.toString() + (short) (Math.random() * 100);
    }

    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = LocalDateTime.now();
        this.id = createDate.toString() + (short) (Math.random() * 100);
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

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%s : %s : %s : %s : %s", id, name, login, email, createDate.toString());
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
        if (!login.equals(user.login)) {
            return false;
        }
        if (!email.equals(user.email)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
