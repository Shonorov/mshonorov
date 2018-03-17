package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Custom stack LIFO.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleStack<E> {
    /**
     * List size.
     * First position in the list.
     * Last position in the list.
     */
    private int size = 0;
    private Element<E> first;
    private Element<E> last;
    /**
     * Get LIFO element and remove it.
     * @return next element.
     */
    public E poll() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        E result = last.getValue();
        if (first == last) {
            first = null;
            last = null;
        } else {
            last.getPrev().setNext(null);
            last = null;
        }
        size--;
        return result;
    }
    /**
     * Add element to queue.
     * @param value element to add.
     */
    public void push(E value) {
        if (first == null) {
            first = new Element<E>(null, value, null);
            last = first;
        } else {
            Element<E> current = last;
            last = new Element<E>(current, value, null);
            current.setNext(last);
        }
        size++;
    }
}
