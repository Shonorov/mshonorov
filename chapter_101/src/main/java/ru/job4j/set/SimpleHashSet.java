package ru.job4j.set;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom hash set.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashSet<E> implements Iterable<E> {
    /**
     * Custom set array.
     * Set load factor.
     * Number of elements.
     * Number of modifications.
     */
    private E[] set;
    private static final double LOAD_FACTOR = 0.75;
    private int size;
    private int modCount = 0;

    public SimpleHashSet(E[] objects) {
        this.set = objects;
    }

    public int getSize() {
        return size;
    }
    /**
     * Adds element to the array if it is not present there.
     * Extends array size if load factor more than 0.75.
     * @param e element to add.
     * @return true if success.
     */
    public boolean add(E e) {
        if (size / set.length >= LOAD_FACTOR) {
            set = Arrays.copyOf(set, set.length * 2);
        }
        if (e == null) {
            set[0] = e;
            size++;
            modCount++;
        } else if (!contains(e)) {
            set[indexFor(hash(e.hashCode()), set.length)] = e;
            size++;
            modCount++;
        }
        return true;
    }
    /**
     * Check if array contains element.
     * @param e element to check.
     * @return true if contains.
     */
    public boolean contains(E e) {
        boolean result = false;
        E current = set[indexFor(hash(e.hashCode()), set.length)];
        if (current != null && current.equals(e)) {
            result = true;
        }
        return result;
    }
    /**
     * Removes element from array.
     * @param e element to remove.
     * @return true if success or no such element.
     */
    public boolean remove(E e) {
        if (contains(e)) {
            set[indexFor(hash(e.hashCode()), set.length)] = null;
            size--;
            modCount++;
        }
        return true;
    }
    /**
     * Generates hash from value's hashcode.
     * @param h value's hashcode.
     * @return hash.
     */
    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    /**
     * Generates array index for element in array with given length.
     * @param h hash(int h) result.
     * @param length set size.
     * @return array index.
     */
    private int indexFor(int h, int length) {
        return h & (length - 1);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int iterPosition = 0;
            private int iterModcount = modCount;

            @Override
            public boolean hasNext() {
                boolean result = false;
                for (int i = iterPosition; i < set.length; i++) {
                    if (set[i] != null) {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public E next() {
                if (iterModcount < modCount) {
                    throw new ConcurrentModificationException();
                }
                E result = null;
                for (int i = iterPosition; i < set.length; i++) {
                    if (set[i] != null) {
                        result = set[iterPosition++];
                        break;
                    } else {
                        iterPosition++;
                    }
                }
                if (result == null) {
                    throw new NoSuchElementException();
                }
                return result;
            }
        };
    }
}
