package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array custom iterator.
 * Iterates only element elements in array.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class EvenNumbersIterator implements Iterator {
    /**
     * Iterable array.
     * Current position in array.
     */
    private final int[] array;
    private int position = 0;

    public EvenNumbersIterator(final int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = position; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        int result = 1;
        for (int i = position; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                result = array[i];
                position++;
                break;
            } else {
                position++;
            }
        }
        if (result == 1 || array.length == 0) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
