/*
Given the head of a singly linked list, find the length of the loop in the linked list if it exists. Return the length of the loop if it exists; otherwise, return 0.
A loop exists in a linked list if some node in the list can be reached again by continuously following the next pointer. Internally, pos is used to denote the index (0-based) of the node from where the loop starts.
Note that pos is not passed as a parameter.

Input: head -> 1 -> 2 -> 3 -> 4 -> 5, pos = 1
Output: 4
Explanation: 2 -> 3 -> 4 -> 5 - >2, length of loop = 4.
 */

//Detecting cycle takes O(n), counting cycle length takes O(k), so overall O(n).
class Solution {

    private int findLength(ListNode slow, ListNode fast) {

        int count = 1; // Start counting from 1 (we are already at meeting node)
        fast = fast.next; // Move fast one step ahead

        // Traverse the entire cycle until we reach the same node again
        while (fast != slow) {
            count++; // Increment cycle length
            fast = fast.next; // Move one step at a time inside cycle
        }

        return count; // Total number of nodes in the loop
    }

    public int findLengthOfLoop(ListNode head) {

        // Initialize slow and fast pointers
        ListNode fast = head;
        ListNode slow = head;

        // Step 1: Detect cycle using Floyd’s Cycle Detection Algorithm
        while (fast != null && fast.next != null) {

            slow = slow.next; // Move 1 step
            fast = fast.next.next; // Move 2 steps

            // If both pointers meet → cycle exists
            if (slow == fast) {

                // Step 2: Calculate and return cycle length
                return findLength(slow, fast);
            }
        }

        // If no cycle found
        return 0;
    }
}