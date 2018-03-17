package ru.job4j.list;
import org.junit.Test;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleStackTest {

    @Test (expected = NoSuchElementException.class)
    public void whenPushTwoThenPollSecondAndFirst() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("first");
        stack.push("second");
        assertThat(stack.poll(), is("second"));
        assertThat(stack.poll(), is("first"));
        stack.poll();
    }
}
