/* https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/description/

You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an additional child pointer. This child pointer may or may not point to a separate doubly linked list, also containing these special nodes. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure as shown in the example below.

Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level, doubly linked list. Let curr be a node with a child list. The nodes in the child list should appear after curr and before curr.next in the flattened list.

Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.
*/

class Solution {

    public Node flatten(Node head) {

        // Edge case: empty list
        if (head == null)
            return head;

        Node curr = head;

        // Traverse the list
        while (curr != null) {

            // If current node has a child list
            if (curr.child != null) {

                // Save the next node because it will be reattached later
                Node next = curr.next;

                // Flatten the child list recursively and attach it after curr
                curr.next = flatten(curr.child);
                curr.next.prev = curr;

                // Child pointer must be removed after flattening
                curr.child = null; // forgot this which gave wrong answer

                // Move to the tail of the flattened child list
                while (curr.next != null) {
                    curr = curr.next;
                }

                // Reattach the original next node after the child list
                if (next != null) {
                    next.prev = curr;
                    curr.next = next;
                }
            }

            // Move to the next node
            curr = curr.next;
        }

        return head;
    }
}

/*
 * Whenever a node has a child list:
 * Save next
 * Flatten the child list
 * Attach child list after current node
 * Go to the end of child list
 * Attach saved next
 * TC - O(N)
 */
