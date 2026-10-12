package analyzer;

/**
 * Fixed-capacity integer array with manual insert, delete,
 * search and display (no java.util collections used).
 */
public class ArrayOperations {

    private int[] data;   // storage
    private int size;     // how many elements are actually used

    public ArrayOperations(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public int getSize()      { return size; }
    public int getCapacity()  { return data.length; }
    public boolean isEmpty()  { return size == 0; }
    public boolean isFull()   { return size == data.length; }

    /** Insert at the end. O(1). */
    public boolean insertAtEnd(int value) {
        if (isFull()) {
            System.out.println("Array is full. Cannot insert.");
            return false;
        }
        data[size++] = value;
        return true;
    }

    /** Insert at a given index, shifting elements to the right. O(n). */
    public boolean insertAtIndex(int index, int value) {
        if (isFull()) {
            System.out.println("Array is full. Cannot insert.");
            return false;
        }
        if (index < 0 || index > size) {
            System.out.println("Invalid index. Valid range is 0 to " + size + ".");
            return false;
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
        return true;
    }

    /** Delete the element at an index, shifting elements to the left. O(n). */
    public boolean deleteAtIndex(int index) {
        if (isEmpty()) {
            System.out.println("Array is empty. Nothing to delete.");
            return false;
        }
        if (index < 0 || index >= size) {
            System.out.println("Invalid index. Valid range is 0 to " + (size - 1) + ".");
            return false;
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return true;
    }

    /** Delete the first occurrence of a value. O(n). */
    public boolean deleteByValue(int value) {
        int index = linearSearch(value);
        if (index == -1) {
            System.out.println("Value " + value + " not found.");
            return false;
        }
        return deleteAtIndex(index);
    }

    /** Linear search. Returns the index, or -1 if not found. O(n). */
    public int linearSearch(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /** Prints all elements. */
    public void display() {
        if (isEmpty()) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.print("Array [" + size + "/" + data.length + "]: ");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + (i < size - 1 ? ", " : ""));
        }
        System.out.println();
    }

    /** Returns a copy of the used part of the array (needed later for Searching). */
    public int[] toArray() {
        int[] copy = new int[size];
        for (int i = 0; i < size; i++) {
            copy[i] = data[i];
        }
        return copy;
    }
}