package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom dynamic list with fail-fast logic.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class DynamicList<E> implements Iterable<E> {
    /**
     * Array of objects.
     * Position in array.
     * Count of array modifications.
     */
    private E[] list;
    private int position = 0;
    private int modCount = 0;

    public DynamicList(E[] list) {
        this.list = list;
    }

    /**
     * Add element to the array.
     * @param value to add.
     */
    public void add(E value) {
        if (list.length <= position) {
            list = Arrays.copyOf(list, list.length * 2);
        }
        this.list[position++] = value;
        modCount++;
    }

    /**
     * Get value by index.
     * @param index index.
     * @return value.
     */
    public E get(int index) {
        return list[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private E[] iterList = list;
            private int iterPosition = 0;
            private int iterModCount = modCount;

            @Override
            public boolean hasNext() {
                return iterPosition < position;
            }

            @Override
            public E next() {
                if (iterModCount < modCount) {
                    throw new ConcurrentModificationException();
                }
                if (iterPosition < position) {
                    throw new NoSuchElementException();
                }
                return iterList[iterPosition++];
            }
        };
    }
}
