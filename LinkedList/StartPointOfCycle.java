/* https://leetcode.com/problems/linked-list-cycle-ii/description/
Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
*/
public class StartPointOfCycle {

    public ListNode detectCycle(ListNode head) {
        // Edge case: empty list
        if (head == null)
            return null;

        // Step 1: Initialize slow and fast pointers
        // slow moves 1 step at a time
        // fast moves 2 steps at a time
        ListNode slow = head;
        ListNode fast = head;

        // Step 2: Detect if cycle exists using Floyd’s Algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next; // move 1 step
            fast = fast.next.next; // move 2 steps

            // If both pointers meet → cycle exists
            if (slow == fast) {
                // Step 3: Find start of cycle
                // Move slow back to head
                slow = head;

                // Move both pointers one step at a time
                // The node where they meet again is the cycle start
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                // Return starting node of cycle
                return slow;
            }
        }

        // If fast reaches null → no cycle
        return null;
    }
}
