package ru.job4j.set;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleSetTest {

    @Test
    public void whenAddSameElementsThenNotAdded() {
        SimpleSet<String> set = new SimpleSet<>(new String[3]);
        set.add("first");
        set.add("first");
        assertThat(set.get(0), is("first"));
        assertThat(set.get(1), is(isEmptyOrNullString()));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorThenInWorks() {
        SimpleSet<Integer> set = new SimpleSet<Integer>(new Integer[5]);
        set.add(1);
        set.add(2);
        set.add(3);
        Iterator iter = set.iterator();
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
        SimpleSet<Integer> set = new SimpleSet<>(new Integer[5]);
        set.add(1);
        Iterator iter = set.iterator();
        set.add(2);
        iter.next();
    }
}
