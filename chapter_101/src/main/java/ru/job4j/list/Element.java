package ru.job4j.list;
/**
 * Element of custom linked list with fail-fast logic.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Element<E> {

    private E value;
    private Element prev;
    private Element next;

    public Element(Element prev, E value, Element next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public void setNext(Element next) {
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public Element getNext() {
        return next;
    }

    public Element getPrev() {
        return prev;
    }

    public void setPrev(Element prev) {
        this.prev = prev;
    }
}
