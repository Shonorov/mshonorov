package ru.job4j.map;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MapTest {

    @Test
    public void whenUserMethodsOverride() {
        Map<User, Object> map = new HashMap<>();
        Calendar date = new GregorianCalendar(1986, 5, 6);
        User first = new User("Ivan", 1, date);
        User second = new User("Ivan", 1, date);
        map.put(first, "first");
        map.put(second, "second");
        System.out.println(map);
    }
}
