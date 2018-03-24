package ru.job4j.tree;
/**
 * Node for Binary search tree.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BstNode<E extends Comparable> {

    private E value;
    private BstNode<E> left;
    private BstNode<E> right;

    public BstNode(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public BstNode<E> getLeft() {
        return left;
    }

    public BstNode<E> getRight() {
        return right;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public void setLeft(BstNode<E> left) {
        this.left = left;
    }

    public void setRight(BstNode<E> right) {
        this.right = right;
    }
}
