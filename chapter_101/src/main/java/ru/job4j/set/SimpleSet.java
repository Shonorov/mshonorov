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
     * Checks if value is present in the array.
     * @param value value to check.
     * @return true if it is new one.
     */
    private boolean isNew(E value) {
        boolean isNew = true;
        for (int i = 0; i < position; i++) {
            if (value.equals(list[i])) {
                isNew = false;
                break;
            }
        }
        return isNew;
    }
    /**
     * Adds unique values to array.
     * @param value to add.
     */
    public void add(E value) {
        if (list.length <= position) {
            list = Arrays.copyOf(list, list.length * 2);
        }
        if (isNew(value)) {
            super.add(value);
        }
    }
}