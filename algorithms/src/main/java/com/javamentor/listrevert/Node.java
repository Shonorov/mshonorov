package com.javamentor.listrevert;

public class Node {

    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}
