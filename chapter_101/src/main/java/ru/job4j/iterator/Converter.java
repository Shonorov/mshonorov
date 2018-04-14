package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converts Iterator of iterators to single Iterator.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            /**
             * Current iterator to process.
             */
            private Iterator<Integer> current = it.next();

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (current.hasNext()) {
                    result = current.hasNext();
                } else if (it.hasNext()) {
                    current = it.next();
                    result = current.hasNext();
                }
                return result;
            }

            @Override
            public Integer next() {
                if (current.hasNext()) {
                    return current.next();
                } else if (it.hasNext()) {
                    current = it.next();
                    return current.next();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}