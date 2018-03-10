package ru.job4j.sort;

import java.util.*;
/**
 * User class to sort.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SortUser {
    /**
     * Sorts array of users by name and age.
     * @param users array of users.
     * @return sorted TreeSet.
     */
    public Set<User> sort(List<User> users) {
        Set<User> result = new TreeSet<>();
        result.addAll(users);
        return result;
    }
    /**
     * Sorts list by name length.
     * @param users array of users.
     * @return sorted array.
     */
    public List<User> sortNameLength(List<User> users) {
             Collections.sort(users, new Comparator<User>() {
                 @Override
                 public int compare(User o1, User o2) {
                     return Integer.compare(o1.getName().length(), o2.getName().length());
                 }
             });
        return users;
    }
    /**
     * Sorts array of users by name and age.
     * @param users array of users.
     * @return sorted TreeSet.
     */
    public List<User> sortByAllFields(List<User> users) {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = o1.getName().compareTo(o2.getName());
                return result == 0 ? Integer.compare(o1.getAge(), o2.getAge()) : result;
            }
        });
        return users;
    } }
