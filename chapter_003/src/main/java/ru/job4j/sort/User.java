package ru.job4j.sort;


/**
 * User class to sort.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class User implements Comparable<User> {

    private final String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='"
                + name
                + '\''
                + ", age="
                + age
                + '}';
    }

    @Override
    public int compareTo(User user) {
        int result = this.name.compareTo(user.name);
        return result == 0 ? Integer.compare(this.age, user.age) : result;
    }
}
