package analyzer;

/**
 * Singly linked list built from scratch (no java.util.LinkedList).
 * Positions are 0-based: position 0 is the head.
 */
public class MyLinkedList {

    private Node head;
    private int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() { return head == null; }
    public int size()        { return size; }

    /** Insert a new node at the front. O(1). */
    public void insertAtHead(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /** Insert a new node at the end. O(n) because we walk to the last node. */
    public void insertAtTail(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /** Insert at a position (0 to size). O(n). */
    public boolean insertAtPosition(int position, int value) {
        if (position < 0 || position > size) {
            System.out.println("Invalid position. Valid range is 0 to " + size + ".");
            return false;
        }
        if (position == 0) {
            insertAtHead(value);
            return true;
        }
        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        Node newNode = new Node(value);
        newNode.next = current.next;
        current.next = newNode;
        size++;
        return true;
    }

    /** Delete the first node holding the given value. O(n). */
    public boolean deleteByValue(int value) {
        if (isEmpty()) {
            System.out.println("List is empty. Nothing to delete.");
            return false;
        }
        if (head.data == value) {
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null && current.next.data != value) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Value " + value + " not found.");
            return false;
        }
        current.next = current.next.next;   // skip over the deleted node
        size--;
        return true;
    }

    /** Delete the node at a position (0 to size-1). O(n). */
    public boolean deleteAtPosition(int position) {
        if (isEmpty()) {
            System.out.println("List is empty. Nothing to delete.");
            return false;
        }
        if (position < 0 || position >= size) {
            System.out.println("Invalid position. Valid range is 0 to " + (size - 1) + ".");
            return false;
        }
        if (position == 0) {
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
        return true;
    }

    /** Linear search. Returns the position of the value, or -1 if not found. O(n). */
    public int search(int value) {
        Node current = head;
        int position = 0;
        while (current != null) {
            if (current.data == value) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }

    /** Prints the list as: 10 -> 20 -> 30 -> null */
    public void display() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        System.out.print("Linked List [" + size + " nodes]: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}