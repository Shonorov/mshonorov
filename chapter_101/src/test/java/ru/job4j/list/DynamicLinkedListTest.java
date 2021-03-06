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
public class DynamicLinkedListTest {

    @Test (expected = NoSuchElementException.class)
    public void whenAddElementAndThenGetByIndex() {
        DynamicLinkedList<String> list = new DynamicLinkedList<>();
        list.add("first");
        list.add("second");
        assertThat(list.get(0), is("first"));
        assertThat(list.get(1), is("second"));
        list.get(2);
    }

    @Test
    public void whenIteratorWorks() {
        DynamicLinkedList<String> list = new DynamicLinkedList<>();
        list.add("first");
        list.add("second");
        Iterator<String> iter = list.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("first"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("second"));
        assertThat(iter.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIteratorThenFailFast() {
        DynamicLinkedList<String> list = new DynamicLinkedList<>();
        list.add("first");
        Iterator<String> iter = list.iterator();
        list.add("second");
        iter.next();
    }
}
