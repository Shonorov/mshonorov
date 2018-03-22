package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Jagged array custom iterator.
 * Iterates each element in each sub array.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MatrixIterator implements Iterator {
    /**
     * Iterable array.
     * Current position in array.
     * Array total length.
     */
    private final int[][] value;
    private int position = 0;
    private int arrLength;

    public MatrixIterator(int[][] value) {
        this.value = value;
        int length = 0;
        for (int i = 0; i < value.length; i++) {
            length += value[i].length;
        }
        this.arrLength = length;
    }

    @Override
    public boolean hasNext() {
        return position < arrLength;
    }

    @Override
    public Object next() {
        if (arrLength == 0 || position >= arrLength) {
            throw new NoSuchElementException();
        }
        int current = 0;
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[i].length; j++) {
                if (current == position) {
                    position++;
                    return value[i][j];
                } else {
                    current++;
                }
            }
        }
        return null;
    }
}
