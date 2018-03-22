package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorWorks() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(2, 4);
        tree.add(1, 3);
        Iterator<Integer> iter = tree.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(2));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(4));
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }

    @Test
    public void whenThreeLeavesThenNotBinary() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        tree.add(2, 4);
        assertThat(tree.isBinary(), is(true));
        tree.add(2, 5);
        assertThat(tree.isBinary(), is(false));
    }
}
