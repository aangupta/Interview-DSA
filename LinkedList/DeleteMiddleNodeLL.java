/*
https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/

You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.

The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.

For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
 
Example 1:

Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]

Input: head = [1,2,3,4]
Output: [1,2,4]

Input: head = [2,1]
Output: [2]

*/

class Solution {

    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = slow.next;

        return head;
    }
}
