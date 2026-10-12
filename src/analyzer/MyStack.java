package analyzer;

/**
 * Array-based stack (LIFO) with a fixed capacity.
 * Handles overflow (push when full) and underflow (pop/peek when empty).
 */
public class MyStack {

    private int[] data;
    private int top;      // index of the top element, -1 when empty

    public MyStack(int capacity) {
        data = new int[capacity];
        top = -1;
    }

    public boolean isEmpty() { return top == -1; }
    public boolean isFull()  { return top == data.length - 1; }
    public int size()        { return top + 1; }
    public int capacity()    { return data.length; }

    /** Adds an element on top. O(1). */
    public boolean push(int value) {
        if (isFull()) {
            System.out.println("Stack overflow! Stack is full.");
            return false;
        }
        data[++top] = value;
        return true;
    }

    /** Removes and returns the top element, or null if the stack is empty. O(1). */
    public Integer pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow! Stack is empty.");
            return null;
        }
        return data[top--];
    }

    /** Returns the top element without removing it, or null if empty. O(1). */
    public Integer peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Nothing to peek.");
            return null;
        }
        return data[top];
    }

    /** Prints the stack from top to bottom. */
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("Stack [" + size() + "/" + data.length + "] (top -> bottom):");
        for (int i = top; i >= 0; i--) {
            System.out.println("  | " + data[i] + " |" + (i == top ? "  <- top" : ""));
        }
    }
}