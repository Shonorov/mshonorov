package ru.job4j.list;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Element of custom linked list with fail-fast logic.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class Element<E> {

    @GuardedBy("this")
    private E value;
    @GuardedBy("this")
    private Element prev;
    @GuardedBy("this")
    private Element next;

    public Element(Element prev, E value, Element next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public synchronized void setNext(Element next) {
        this.next = next;
    }

    public synchronized E getValue() {
        return value;
    }

    public synchronized Element getNext() {
        return next;
    }

    public synchronized Element getPrev() {
        return prev;
    }

    public synchronized void setPrev(Element prev) {
        this.prev = prev;
    }
}
