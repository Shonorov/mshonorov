package ru.job4j.list;

import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class DynamicListTest {

    @Test
    public void whenAddValueThenGetIndexAndGrow() {
        DynamicList<Integer> list = new DynamicList<Integer>(new Integer[1]);
        list.add(1);
        list.add(2);
        assertThat(list.get(0), is(1));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorThenInWorks() {
        DynamicList<Integer> list = new DynamicList<Integer>(new Integer[5]);
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator iter = list.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(2));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIteratorThenFailFast() {
        DynamicList<Integer> list = new DynamicList<>(new Integer[5]);
        list.add(1);
        Iterator iter = list.iterator();
        list.add(2);
        iter.next();
    }
}
