package ru.job4j.sort;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void whenArrayThenSortedTreeSet() {
        SortUser sort = new SortUser();
        User user1 = new User("Ivan", 20);
        User user2 = new User("Petr", 30);
        User user3 = new User("Boris", 25);
        User user4 = new User("Petr", 35);
        List<User> source = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        Set<User> expect = new TreeSet<>();
        expect.add(user3);
        expect.add(user1);
        expect.add(user2);
        expect.add(user4);
        assertThat(sort.sort(source), is(expect));
    }

    @Test
    public void whenArrayThenSortedArray() {
        SortUser sort = new SortUser();
        User user1 = new User("Ivan", 20);
        User user2 = new User("Petr", 30);
        User user3 = new User("Boris", 25);
        User user4 = new User("Petr", 35);
        List<User> source = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        List<User> expect = new ArrayList<>();
        expect.add(user3);
        expect.add(user1);
        expect.add(user2);
        expect.add(user4);
        assertThat(sort.sortByAllFields(source), is(expect));
    }

    @Test
    public void whenArrayThenSortedArrayByNameLength() {
        SortUser sort = new SortUser();
        User user1 = new User("Ivan", 20);
        User user2 = new User("Yan", 30);
        User user3 = new User("Boris", 25);
        User user4 = new User("Valera", 35);
        List<User> source = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        List<User> expect = new ArrayList<>();
        expect.add(user2);
        expect.add(user1);
        expect.add(user3);
        expect.add(user4);
        assertThat(sort.sortNameLength(source), is(expect));
    }
}
