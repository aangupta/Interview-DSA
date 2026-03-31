//Approach 1 : Push Costly Operation
class QueueStack {
    Queue<Integer> q;

    public QueueStack() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        int size = q.size();

        q.add(x); // new element added at rear

        // rotate previous elements behind new element
        // so that new element comes to front (stack top)
        for (int i = 0; i < size; i++) {
            q.add(q.peek()); // move front to rear
            q.poll();
        }
    }

    public int pop() {
        int num = q.peek(); // front acts as stack top
        q.poll(); // remove top element
        return num;
    }

    public int top() {
        return q.peek(); // front element is always top
    }

    public boolean isEmpty() {
        return q.size() == 0;
    }
}

// Approach 2 : Pop costly operation:
/*
 * Move first n-1 elements to rear
 * Last remaining element = top
 * Remove it
 */

class QueueStack {
    Queue<Integer> q;

    public QueueStack() {
        q = new LinkedList<>();

    }

    public void push(int x) {
        q.add(x);
    }

    public int pop() {
        int size = q.size();

        for (int i = 0; i < size - 1; i++) {
            q.add(q.poll());
        }

        return q.poll();

    }

    public int top() {
        int size = q.size();

        for (int i = 0; i < size - 1; i++) {
            q.add(q.poll());
        }

        int top = q.peek(); // last element now at front

        q.add(q.poll()); // restore order

        return top;

    }

    public boolean isEmpty() {
        return (q.size() == 0);
    }
}

// Approach: 3 Without Using In-Built Queue
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class QueueStack {
    Node front, rear;
    int size;

    public QueueStack() {
        front = rear = null; // empty queue
        size = 0;
    }

    public void push(int x) {
        Node temp = new Node(x);

        // normal enqueue
        if (front == null) {
            front = rear = temp;
        } else {
            rear.next = temp;
            rear = temp;
        }

        int n = size;

        // rotate previous elements to bring new node to front
        while (n > 0) {
            Node curr = front;
            front = front.next;

            rear.next = curr;
            rear = curr;
            rear.next = null;
            n--;
        }

        size++;
    }

    public int pop() {
        if (front == null) {
            System.out.println("Stack is Empty");
            System.exit(1);
        }

        int val = front.data;

        front = front.next; // remove top (front)
        size--;

        if (front == null)
            rear = null; // reset if empty

        return val;
    }

    public int top() {
        if (front == null) {
            System.out.println("Stack is Empty");
            System.exit(1);
        }

        return front.data; // front acts as stack top
    }

    public boolean isEmpty() {
        return size == 0;
    }
}