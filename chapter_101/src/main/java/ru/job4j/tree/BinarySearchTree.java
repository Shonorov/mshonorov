package ru.job4j.tree;

import java.util.*;

/**
 * Custom binary search tree realization.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BinarySearchTree<E extends Comparable> implements Iterable<E>{

    private BstNode<E> root;

    public BinarySearchTree(E value) {
        this.root = new BstNode<>(value);
    }

    /**
     * Add node to the tree.
     * @param e new node value.
     * @return true if success.
     */
    public boolean add(E e) {
        boolean result = false;
        Queue<BstNode<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Optional<BstNode<E>> current = Optional.of(queue.poll());
            int compare = e.compareTo(current.get().getValue());
            Optional<BstNode<E>> left = Optional.ofNullable(current.get().getLeft());
            Optional<BstNode<E>> right = Optional.ofNullable(current.get().getRight());
            if (compare <= 0) {
                if (left.isPresent()) {
                    queue.offer(left.get());
                } else {
                    current.get().setLeft(new BstNode<>(e));
                    result = true;
                    break;
                }
            } else if (compare > 0) {
                if (right.isPresent()) {
                    queue.offer(right.get());
                } else {
                    current.get().setRight(new BstNode<>(e));
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Queue<BstNode<E>> iterData = fillData();

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
            private LinkedList<BstNode<E>> fillData() {
                LinkedList<BstNode<E>> data = new LinkedList<>();
                Queue<BstNode<E>> dataQueue = new LinkedList<>();
                dataQueue.offer(root);
                data.add(root);
                while (!dataQueue.isEmpty()) {
                    BstNode<E> node = dataQueue.poll();
                    Optional<BstNode<E>> left = Optional.ofNullable(node.getLeft());
                    Optional<BstNode<E>> right = Optional.ofNullable(node.getRight());
                    if (left.isPresent()) {
                        dataQueue.offer(node.getLeft());
                        data.add(left.get());
                    }
                    if (right.isPresent()) {
                        dataQueue.offer(node.getRight());
                        data.add(right.get());
                    }
                }
                return data;
            }
        };
    }
}
