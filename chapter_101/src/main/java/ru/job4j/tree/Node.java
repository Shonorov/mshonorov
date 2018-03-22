package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
/**
 * Node for SimpleTree.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Node<E extends Comparable<E>> {

    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    /**
     * Add child Node.
     * @param child Node.
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Get all children Node list.
     * @return List of Node.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Check if value the same.
     * @param that value.
     * @return true if equals.
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    public E getValue() {
        return value;
    }
}
