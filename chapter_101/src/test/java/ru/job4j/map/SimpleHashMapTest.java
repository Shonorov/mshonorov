package ru.job4j.map;
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
public class SimpleHashMapTest {

    @Test (expected = NoSuchElementException.class)
    public void whenAddDuplicateAndRemoveValueThenContainsAndRightSize() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(new MapEntry[16]);
        map.insert(1, "first");
        assertThat(map.insert(1, "first"), is(false));
        map.insert(2, "second");
        assertThat(map.getSize(), is(2));
        assertThat(map.get(1), is("first"));
        assertThat(map.get(2), is("second"));
        assertThat(map.delete(1), is(true));
        assertThat(map.delete(3), is(false));
        assertThat(map.getSize(), is(1));
        assertThat(map.get(2), is("second"));
        map.get(3);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorWorks() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(new MapEntry[16]);
        map.insert(1, "first");
        map.insert(2, "second");
        Iterator<MapEntry<Integer, String>> iter = map.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getValue(), is("first"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getValue(), is("second"));
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIteratorThenFailFast() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(new MapEntry[16]);
        map.insert(1, "first");
        Iterator<MapEntry<Integer, String>> iter = map.iterator();
        map.insert(2, "second");
        iter.next();
    }
}
