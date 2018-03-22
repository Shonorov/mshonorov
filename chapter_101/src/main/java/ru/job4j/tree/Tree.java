package ru.job4j.tree;

import java.util.*;

/**
 * Custom SimpleTree realization.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;

    public Tree(E value) {
        this.root = new Node<>(value);
    }

    /**
     * Add unique elements to the tree.
     * @param parent parent.
     * @param child  child.
     * @return true if success.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> parentNode = findBy(parent);
        if (parentNode.isPresent() && !findBy(child).isPresent()) {
            parentNode.get().add(new Node<>(child));
            result = true;
        }
        return result;
    }

    /**
     * Find node by value.
     * @param value value to find.
     * @return Optional of Node.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Queue<Node<E>> iterData = fillData();

            @Override
            public boolean hasNext() {
                return !iterData.isEmpty();
            }

            @Override
            public E next() {
                if (iterData.isEmpty()) {
                    throw new NoSuchElementException();
                }
                return iterData.poll().getValue();
            }

            /**
             * Fills Queue with all nodes in this tree;
             * @return all node list.
             */
            private LinkedList<Node<E>> fillData() {
                LinkedList<Node<E>> data = new LinkedList<>();
                Queue<Node<E>> dataQueue = new LinkedList<>();
                dataQueue.offer(root);
                data.add(root);
                while (!dataQueue.isEmpty()) {
                    Node<E> node = dataQueue.poll();
                    for (Node<E> leaf : node.leaves()) {
                        dataQueue.offer(leaf);
                        data.add(leaf);
                    }
                }
                return data;
            }
        };
    }


}
