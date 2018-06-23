package ru.job4j.cars.model;
/**
 * Model for car shop user web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class User {

    private Integer id;
    private String name;
    private String login;
    private String password;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return result;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id
                + ", name='" + name
                + ", login='" + login
                + ", password='" + password + '}';
    }
}
