package ru.job4j.search;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class UserConvertTest {

    @Test
    public void whenUserConvertThenReturnMap() {
        UserConvert converter = new UserConvert();
        User user1 = new User(1, "Name1");
        User user2 = new User(2, "Name2");
        User user3 = new User(3, "Name3");
        List<User> source = new ArrayList<>(Arrays.asList(user1, user2, user3));
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(1, user1);
        expect.put(2, user2);
        expect.put(3, user3);
        assertThat(converter.process(source), is(expect));
    }
}
