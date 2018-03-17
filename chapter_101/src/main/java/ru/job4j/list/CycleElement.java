package ru.job4j.list;
/**
 * Element of custom cycled list.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CycleElement<E> {
    /**
     * Element value.
     * Next element.
     */
    private E value;
    private CycleElement next;

    public CycleElement(E value) {
        this.value = value;
    }

    public void setNext(CycleElement next) {
        this.next = next;
    }

    public CycleElement<E> next() {
        return this.next;
    }
}
