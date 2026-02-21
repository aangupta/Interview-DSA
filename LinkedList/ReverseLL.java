/*
https://leetcode.com/problems/reverse-linked-list/description/
Given the head of a singly linked list, reverse the list, and return the reversed list.

Example 1:

Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
*/

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode front = null;

        while (curr != null) {
            front = curr.next;
            curr.next = prev;
            prev = curr;
            curr = front;
        }

        return prev;

    }
}