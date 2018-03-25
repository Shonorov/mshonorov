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

    /**
     * Check if value is simple number.
     * @param number to check.
     * @return true if simple.
     */
    private boolean isSimple(int number) {
        boolean result = number != 1;
        for (int j = 2; j < number; j++) {
            if (number % j == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = position; i < values.length; i++) {
            if (isSimple(values[i])) {
                position = i;
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return values[position++];
    }
}
