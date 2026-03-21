/* https://leetcode.com/problems/copy-list-with-random-pointer/description/

A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.
*/

//Using HashMap
// TC - O(n + n)
// SC - O(n)
class Solution {

    public Node copyRandomList(Node head) {

        // Edge case: if the original list is empty
        if (head == null)
            return head;

        // original node -> copied node
        Map<Node, Node> map = new HashMap<>();

        // Pointer to traverse original list
        Node curr = head;

        // First pass:
        // Create copy of each node and store in map
        while (curr != null) {

            // Create a new node with same value
            Node copyNode = new Node(curr.val);

            map.put(curr, copyNode);

            curr = curr.next;
        }

        curr = head;

        // Second pass:
        // Assign next and random pointers for copied nodes
        while (curr != null) {

            // Get the copied node corresponding to current node
            Node copyNode = map.get(curr);

            // Set next pointer
            copyNode.next = map.get(curr.next);

            // Set random pointer
            copyNode.random = map.get(curr.random);

            curr = curr.next;
        }

        // Return the copied head
        return map.get(head);
    }
}

// Optimised Solution TC - O(n) SC - O(1)
class Solution {

    public Node copyRandomList(Node head) {
        if (head == null)
            return head;

        Node curr = head;

        // Step 1: Insert copied nodes in between original nodes
        // Example:
        // A -> B -> C
        // becomes
        // A -> A' -> B -> B' -> C -> C'
        while (curr != null) {
            Node copyNode = new Node(curr.val);
            copyNode.next = curr.next;
            curr.next = copyNode;

            curr = curr.next.next;
        }

        // Step 2: Assign random pointers for copied nodes
        // If original node's random points to R,
        // copied node's random should point to R'
        curr = head;
        while (curr != null) {
            Node copyNode = curr.next;

            if (curr.random != null) // always check (runtime error) mistake
                copyNode.random = curr.random.next;

            curr = curr.next.next;
        }

        // Step 3: Separate the copied list from the interleaved list
        // Restore original list and build the copied list
        Node dummy = new Node(-1);
        Node res = dummy;
        curr = head;

        while (curr != null) {
            res.next = curr.next; // add copied node to result list
            curr.next = curr.next.next; // restore original list

            res = res.next;
            curr = curr.next;
        }

        return dummy.next;
    }
}