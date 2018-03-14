package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array custom iterator.
 * Iterates only simple numbers.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class PrimeIterator implements Iterator {
    /**
     * Iterable array.
     * Current position in array.
     */
    private final int[] values;
    private int position = 0;

    public PrimeIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = position; i < values.length; i++) {
            result = true;
            for (int j = 2; j < values[i]; j++) {
                if (values[i] % j == 0) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        int result = 0;
        for (int i = position; i < values.length; i++) {
            result = values[i];
            if (result == 1) {
                position++;
                continue;
            }
            for (int j = 2; j < values[i]; j++) {
                if (values[i] % j == 0) {
                    result = 0;
                    position++;
                    break;
                }
            }
            if (result != 0) {
                position++;
                break;
            }
        }
        if (values.length == 0 || result == 0) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
