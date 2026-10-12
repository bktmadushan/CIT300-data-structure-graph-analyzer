package analyzer;

/**
 * Circular-array queue (FIFO) with a fixed capacity.
 * Handles enqueue when full and dequeue/peek when empty.
 */
public class MyQueue {

    private int[] data;
    private int front;    // index of the first element
    private int rear;     // index of the last element
    private int count;    // number of elements currently stored

    public MyQueue(int capacity) {
        data = new int[capacity];
        front = 0;
        rear = -1;
        count = 0;
    }

    public boolean isEmpty() { return count == 0; }
    public boolean isFull()  { return count == data.length; }
    public int size()        { return count; }
    public int capacity()    { return data.length; }

    /** Adds an element at the rear. O(1). */
    public boolean enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue.");
            return false;
        }
        rear = (rear + 1) % data.length;   // wrap around the array
        data[rear] = value;
        count++;
        return true;
    }

    /** Removes and returns the front element, or null if the queue is empty. O(1). */
    public Integer dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return null;
        }
        int value = data[front];
        front = (front + 1) % data.length;
        count--;
        return value;
    }

    /** Returns the front element without removing it, or null if empty. O(1). */
    public Integer peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Nothing at the front.");
            return null;
        }
        return data[front];
    }

    /** Prints the queue from front to rear. */
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.print("Queue [" + count + "/" + data.length + "] (front -> rear): ");
        for (int i = 0; i < count; i++) {
            System.out.print(data[(front + i) % data.length] + (i < count - 1 ? ", " : ""));
        }
        System.out.println();
    }
}