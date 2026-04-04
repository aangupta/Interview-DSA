class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null; // standalone node
    }
}

class LinkedListQueue {
    Node start, end;
    int size;

    public LinkedListQueue() {
        start = null; // empty queue
        end = null;
        size = 0;
    }

    public void push(int x) {
        Node temp = new Node(x);

        if (start == null) {
            start = end = temp; // first element initializes both pointers
        } else {
            end.next = temp; // link new node after current rear
            end = temp; // move rear to new node
        }

        size++;
    }

    public int pop() {
        if (start == null) { // underflow
            System.out.println("Queue is Empty");
            System.exit(1);
        }

        int popped = start.data;

        start = start.next; // move front forward
        size--;

        if (start == null) {
            end = null; // queue becomes empty, reset rear
        }

        return popped;
    }

    public int peek() {
        if (start == null) { // no element to access
            System.out.println("Queue is Empty");
            System.exit(1);
        }

        return start.data; // front element
    }

    public boolean isEmpty() {
        return size == 0; // no nodes present
    }
}