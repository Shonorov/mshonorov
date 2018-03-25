package ru.job4j.list;

import java.util.Optional;

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
        if (firstElement != null) {
            Optional<CycleElement<E>> slow = Optional.of(firstElement);
            Optional<CycleElement<E>> fast = Optional.of(firstElement);
            while (true) {
                slow = Optional.ofNullable(slow.get().next());
                if (fast.get().next() != null) {
                    fast = Optional.ofNullable(slow.get().next().next());
                } else {
                    fast = Optional.empty();
                }
                if (!slow.isPresent() || !fast.isPresent()) {
                    break;
                }
                if (slow.get().equals(fast.get())) {
                    result = true;
                    break;
                }
            }
        }
        return  result;
    }

}
