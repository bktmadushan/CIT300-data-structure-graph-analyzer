package analyzer;

/**
 * One node of a singly linked list: holds a value and a
 * reference to the next node (null at the end of the list).
 */
public class Node {

    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}