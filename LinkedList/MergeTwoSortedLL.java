/* https://leetcode.com/problems/merge-two-sorted-lists/description/
You are given the heads of two sorted linked lists list1 and list2.
Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.

Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: list1 = [], list2 = []
Output: []
Example 3:

Input: list1 = [], list2 = [0]
Output: [0]
 */

// Approach 1: Iterative: TC - O(m + n) SC - O(1) 
class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create a dummy node to simplify edge cases
        // (like handling head of merged list)
        ListNode dummy = new ListNode(-1);

        // Pointer to build the new merged list
        ListNode curr = dummy;

        // Traverse both lists until one becomes null
        while (list1 != null && list2 != null) {
            // Compare current nodes and attach the smaller one
            if (list1.val <= list2.val) {
                curr.next = list1; // Link list1 node
                list1 = list1.next; // Move list1 forward
            } else {
                curr.next = list2; // Link list2 node
                list2 = list2.next; // Move list2 forward
            }

            // Move merged list pointer forward
            curr = curr.next;
        }

        // Attach the remaining part of the non-empty list
        // Only one of list1 or list2 will be non-null
        curr.next = (list1 != null) ? list1 : list2;

        // Return the head of the merged list (skip dummy node)
        return dummy.next;
    }
}

// Appraoch 2: Recursive TC - O(m+n) SC - O(m+n) recursive stack space

class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Base Case:
        // If one list is empty, return the other
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;

        // Recursive Case:
        // Choose smaller node and recursively merge remaining lists
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
/*
 * In recursion, the first returned smallest node automatically becomes the head
 * — so no dummy node is required.
 */
