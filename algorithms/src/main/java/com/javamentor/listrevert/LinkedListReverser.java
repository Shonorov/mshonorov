package com.javamentor.listrevert;

/**
 * Change order of the linked list elements.
 */
public class LinkedListReverser {

    private Node head;

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getHead() {
        return head;
    }

    public void revert() {
        Node prev = null, current = this.head, next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        this.head = prev;
    }

    public void reverseRecursive(Node curr, Node prev) {
        /* If last node mark it head*/
        if (curr.next == null) {
            this.head = curr;
            /* Update next to prev node */
            curr.next = prev;
            return;
        }
        /* Save curr->next node for recursive call */
        Node next = curr.next;
        /* and update next ..*/
        curr.next = prev;
        reverseRecursive(next, curr);
    }

    public void printList() {
        Node current = this.head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

}
