package ru.job4j.set;

import ru.job4j.list.DynamicLinkedList;
import ru.job4j.list.Element;
/**
 * Custom set based on linked list realization.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleLinkedSet<E> extends DynamicLinkedList<E> {
    /**
     * Add element to the Linked Set.
     * @param value to add.
     */
    @Override
    public void add(E value) {
        boolean exists = false;
        if (size == 0) {
            super.add(value);
        } else {
            Element<E> current = first;
            for (int i = 0; i < size; i++) {
                if (current.getValue().equals(value)) {
                    exists = true;
                    break;
                }
                current = current.getNext();
            }
            if (!exists) {
                super.add(value);
            }
        }
    }
}
