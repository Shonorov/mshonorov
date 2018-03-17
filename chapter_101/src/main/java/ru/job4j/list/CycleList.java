package ru.job4j.list;
/**
 * List of Element with cycle.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CycleList<E> {
    /**
     * List size.
     * First element of the list.
     * Last element of the list.
     */
    private int size;
    private CycleElement<E> first;
    private CycleElement<E> last;

    public CycleList() {
        this.size = 0;
    }

    /**
     * Add element next to last one.
     * @param element to add.
     */
    public void add(CycleElement<E> element) {
        if (first == null) {
            first = element;
            last = element;
        } else {
            last.setNext(element);
            last = element;
        }
        size++;
    }
    /**
     * Check if list has cycles.
     * @param firstElement element to start search from.
     * @return true if cycled.
     */
    public boolean hasCycle(CycleElement<E> firstElement) {
        boolean result = false;
        CycleElement<E>[] elements = new CycleElement[size];
        int position = 0;
        CycleElement<E> current = firstElement;
        while (current.next() != null) {
            elements[position++] = current;
            current = current.next();
            for (int j = 0; j < position; j++) {
                if (elements[j].equals(current)) {
                    result = true;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return  result;
    }

}
