package ru.job4j.generic;

import java.util.Iterator;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayTest {

    @Test
    public void whenAddThenGetRightValue() {
        SimpleArray<String> array = new SimpleArray(new String[5]);
        String first = "first";
        assertThat(array.add(first), is(true));
        assertThat(array.get(0), is(first));
    }

    @Test
    public void whenSetThenGetRightValue() {
        SimpleArray<String> array = new SimpleArray(new String[5]);
        array.add("second");
        array.add("third");
        String first = "first";
        array.set(0, first);
        assertThat(array.get(0), is(first));
        assertThat(array.get(2), is("third"));
    }

    @Test
    public void whenDeleteThenGetRightValue() {
        SimpleArray<String> array = new SimpleArray(new String[5]);
        String first = "first";
        array.add("zero");
        array.add(first);
        array.delete(0);
        assertThat(array.get(0), is(first));
        assertThat(array.get(1), isEmptyOrNullString());
    }

    @Test
    public void whenIteratorWorks() {
        SimpleArray<String> array = new SimpleArray(new String[5]);
        array.add("first");
        array.add("second");
        array.add("third");
        Iterator<String> iter = array.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("first"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("second"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("third"));
        assertThat(iter.hasNext(), is(false));
    }
}
