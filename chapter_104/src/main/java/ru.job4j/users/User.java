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
    private String password;
    private String role;
    private String country;
    private String city;

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
        this.password = login;
        this.role = "user";
        this.country = "0";
        this.city = "0";
    }

    public User(String name, String login, String email, String password, String role, String country, String city) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = LocalDateTime.now();
        this.id = createDate.toString() + (short) (Math.random() * 100);
        this.password = password;
        this.role = role;
        this.country = country;
        this.city = city;
    }

    public User(String id, String name, String login, String email, LocalDateTime createDate, String password, String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.password = password;
        this.role = role;
        this.country = "Russia";
        this.city = "Moscow";
    }

    public User(String id, String name, String login, String email, LocalDateTime createDate, String password, String role, String country, String city) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.password = password;
        this.role = role;
        this.country = country;
        this.city = city;
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

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("%s : %s : %s : %s : %s : %s : %s : %s : %s", id, name, login, email, createDate.toString(), password, role, country, city);
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
        if (!country.equals(user.country)) {
            return false;
        }
        if (!city.equals(user.city)) {
            return false;
        }
        return role.equals(user.role);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
