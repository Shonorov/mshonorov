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
public class SimpleQueueTest {

    @Test (expected = NoSuchElementException.class)
    public void whenPushTwoThenPollFirstAndSecond() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        queue.push("first");
        queue.push("second");
        assertThat(queue.poll(), is("first"));
        assertThat(queue.poll(), is("second"));
        queue.poll();
    }
}
