package ru.job4j.tree;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BinarySearchTreeTest {
    @Test (expected = NoSuchElementException.class)
    public void whenIteratorWorks() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(5);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        Iterator<Integer> iter = tree.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(5));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(4));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(6));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(5));
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }
}
