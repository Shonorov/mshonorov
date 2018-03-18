package ru.job4j.set;

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
public class SimpleHashSetTest {

    @Test
    public void whenAddDuplicateAndRemoveValueThenContainsAndRightSize() {
        SimpleHashSet<String> set = new SimpleHashSet<>(new String[16]);
        set.add("first");
        assertThat(set.contains("first"), is(true));
        set.add("first");
        assertThat(set.getSize(), is(1));
        set.remove("first");
        assertThat(set.contains("first"), is(false));
        assertThat(set.getSize(), is(0));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorWorks() {
        SimpleHashSet<String> set = new SimpleHashSet<>(new String[16]);
        set.add("first");
        set.add("second");
        Iterator<String> iter = set.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("second"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("first"));
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIteratorThenFailFast() {
        SimpleHashSet<String> set = new SimpleHashSet<>(new String[16]);
        set.add("first");
        Iterator<String> iter = set.iterator();
        set.add("second");
        iter.next();
    }
}
