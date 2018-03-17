package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Custom queue FIFO.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueue<E> {
    /**
     * List size.
     * First position in the list.
     * Last position in the list.
     */
    private int size = 0;
    private Element<E> first;
    private Element<E> last;
    /**
     * Get FIFO element and remove it.
     * @return next element.
     */
    public E poll() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        E result = first.getValue();
        if (first == last) {
            first = null;
            last = null;
        } else {
            first.getNext().setPrev(null);
            first = first.getNext();
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
