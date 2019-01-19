package com.javamentor.listrevert;

import org.junit.Test;

public class LinkedListReverserTest {

    @Test
    public void whenLinkedListRevertedThenPrint() {
        LinkedListReverser reverser = new LinkedListReverser();
        Node node5 = new Node(5);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        reverser.setHead(node1);
        System.out.println("Before:");
        reverser.printList();
        reverser.revert();
        System.out.println("After:");
        reverser.printList();
        reverser.reverseRecursive(reverser.getHead(), null);
        System.out.println("Recursive:");
        reverser.printList();
    }
}