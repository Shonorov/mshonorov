package ru.job4j.set;

import ru.job4j.list.DynamicList;

import java.util.Arrays;
/**
 * Custom set realization.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleSet<E> extends DynamicList<E> {

    public SimpleSet(E[] list) {
        super(list);
    }
    /**
     * Adds unique values to array.
     * @param value to add.
     */
    public void add(E value) {
        if (list.length <= position) {
            list = Arrays.copyOf(list, list.length * 2);
        }
        if (!contains(value)) {
            super.add(value);
        }
    }
}