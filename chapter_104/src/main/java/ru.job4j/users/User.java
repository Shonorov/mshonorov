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

    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = LocalDateTime.now();
        this.id = createDate.toString() + (short) (Math.random() * 100);
    }

    @Override
    public String toString() {
        return String.format("%s : %s : %s : %s : %s", id, name, login, email, createDate.toString());
    }
}
