
class ArrayStack {
    // Array to hold elements
    private int[] stackArray;
    // Maximum capacity
    private int capacity;
    // Index of top element
    private int topIndex;

    // Constructor
    public ArrayStack(int size) {
        capacity = size;
        stackArray = new int[capacity];
        // Initialize stack as empty
        topIndex = -1;
    }

    public ArrayStack() {
        this(1000);
    }

    // Pushes element x
    public void push(int x) {
        if (topIndex >= capacity - 1) {
            System.out.println("Stack overflow");
            return;
        }
        stackArray[++topIndex] = x;
    }

    // Removes and returns top element
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            // Return invalid value
            return -1;
        }
        return stackArray[topIndex--];
    }

    // Returns top element
    public int top() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return stackArray[topIndex];
    }

    /*
     * Returns true if the
     * stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return topIndex == -1;
    }
}
