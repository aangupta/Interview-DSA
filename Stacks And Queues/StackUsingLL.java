class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null; // new node initially points nowhere
    }
}

class LinkedListStack {
    Node top;
    int size;

    public LinkedListStack() {
        top = null; // indicates empty stack
        size = 0;
    }

    public void push(int x) {
        Node temp = new Node(x);

        temp.next = top; // link new node to previous top
        top = temp; // update top to new node

        size++;
    }

    public int pop() {
        if (top == null) { // underflow condition
            System.out.println("Stack is Empty");
            System.exit(1);
        }

        int popped = top.data;

        top = top.next; // move top to next node (removes current top)
        size--;

        return popped;
    }

    public int top() {
        if (top == null) { // no element to return
            System.out.println("Stack is Empty");
            System.exit(1);
        }

        return top.data; // always points to latest inserted element
    }

    public boolean isEmpty() {
        return size == 0; // stack empty when no nodes exist
    }
}