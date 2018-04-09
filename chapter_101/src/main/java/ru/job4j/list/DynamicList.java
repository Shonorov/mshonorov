package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
/**
 * Custom dynamic list with fail-fast logic.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class DynamicList<E> implements Iterable<E> {
    /**
     * Array of objects.
     * Position in array.
     * Count of array modifications.
     */
    @GuardedBy("this")
    protected E[] list;
    @GuardedBy("this")
    protected int position = 0;
    @GuardedBy("this")
    private int modCount = 0;

    private synchronized E getElement(int index) {
        return list[index];
    }

    private synchronized int getPosition() {
        return position;
    }

    private synchronized int getModCount() {
        return modCount;
    }

    public DynamicList(E[] list) {
        this.list = list;
    }

    /**
     * Check if array contains element.
     * @param element to check.
     * @return true if contains.
     */
    public synchronized boolean contains(E element) {
        boolean result = false;
        for (int i = 0; i < position; i++) {
            if (element.equals(list[i])) {
                result = true;
                break;
            }
        }
        return result;
    }
    /**
     * Add element to the array.
     * @param value to add.
     */
    public synchronized void add(E value) {
        if (list.length <= position) {
            list = Arrays.copyOf(list, list.length * 2);
        }
        list[position++] = value;
        modCount++;
    }
    /**
     * Get value by index.
     * @param index index.
     * @return value.
     */
    public synchronized E get(int index) {
        return this.list[index];
    }


    @Override
    public synchronized Iterator<E> iterator() {
        return new Iterator<E>() {

            private int iterPosition = 0;
            private int iterModCount = getModCount();

            @Override
            public boolean hasNext() {
                return iterPosition < getPosition();
            }

            @Override
            public E next() {
                if (iterModCount < getModCount()) {
                    throw new ConcurrentModificationException();
                }
                if (iterPosition >= getPosition()) {
                    throw new NoSuchElementException();
                }
                return getElement(iterPosition++);
            }
        };
    }
}
