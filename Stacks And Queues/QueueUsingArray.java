class ArrayQueue {
    int[] queue; // Array to store queue elements
    int capacity; // Maximum size of queue
    int start, end; // Pointers: start = front, end = rear
    int currSize; // Current number of elements in queue

    public ArrayQueue() {
        capacity = 10; // Default capacity
        queue = new int[capacity];
        currSize = 0;
        start = -1; // -1 indicates empty queue
        end = -1;
    }

    // Push element to rear of queue
    public void push(int x) {

        // Overflow check
        if (currSize == capacity) {
            System.out.println("Queue is full\nExiting...");
            System.exit(1);
        }

        // First element insertion
        if (end == -1) {
            start = 0;
            end = 0;
        } else {
            // Circular increment of rear pointer
            end = (end + 1) % capacity;
        }

        queue[end] = x; // Insert element
        currSize++; // Increase size
    }

    // Remove element from front
    public int pop() {

        // Underflow check
        if (start == -1) {
            System.out.println("Queue Empty\nExiting...");
            System.exit(1);
        }

        int popped = queue[start];

        // If only one element was present
        if (currSize == 1) {
            start = -1;
            end = -1;
        } else {
            // Move front pointer circularly
            start = (start + 1) % capacity;
        }

        currSize--; // Decrease size
        return popped;
    }

    // Get front element without removing
    public int peek() {
        if (start == -1) {
            System.out.println("Queue is Empty");
            System.exit(1);
        }

        return queue[start];
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return (currSize == 0);
    }
}