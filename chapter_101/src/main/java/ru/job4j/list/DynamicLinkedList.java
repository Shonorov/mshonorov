package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
/**
 * Custom dynamic linked list with fail-fast logic.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class DynamicLinkedList<E> implements Iterable<E> {
    /**
     * List size.
     * First position in the list.
     * Last position in the list.
     * Count of array modifications.
     */
    protected int size = 0;
    @GuardedBy("this")
    protected Element<E> first;
    @GuardedBy("this")
    protected Element<E> last;
    protected int modCount = 0;

    public DynamicLinkedList() {
    }
    /**
     * Check if array contains element.
     * @param element to check.
     * @return true if contains.
     */
    public synchronized boolean contains(E element) {
        boolean result = false;
        if (first != null) {
            Element<E> current = first;
            while (current != null) {
                if (current.getValue().equals(element)) {
                    result = true;
                    break;
                }
                current = current.getNext();
            }
        }
        return result;
    }
    /**
     * Add element to the Linked List.
     * @param value to add.
     */
    public synchronized void add(E value) {
        if (first == null) {
            first = new Element<E>(null, value, null);
            last = first;
            size++;
            modCount++;
        } else {
            Element<E> current = last;
            last = new Element<E>(current, value, null);
            current.setNext(last);
            size++;
            modCount++;
        }
    }
    /**
     * Get value by index.
     * @param index index.
     * @return value.
     */
    public synchronized E get(int index) {
        if (index >= size) {
            throw new NoSuchElementException();
        }
        Element<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.getNext();
        }
        return result.getValue();
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Iterator current element.
             * Iterator current position.
             * Before iterator created modifications count.
             */
            @GuardedBy("this")
            Element<E> current = first;
            private int iterPosition = 0;
            private int iterModCount = modCount;

            @Override
            public boolean hasNext() {
                return iterPosition < size;
            }

            @Override
            public synchronized E next() {
                if (iterModCount < modCount) {
                    throw new ConcurrentModificationException();
                }
                if (iterPosition >= size) {
                    throw new NoSuchElementException();
                }
                if (iterPosition != 0) {
                    current = current.getNext();
                }
                iterPosition++;
                return current.getValue();
            }
        };
    }
}

