/*
https://leetcode.com/problems/palindrome-linked-list/description/
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
*/

//Approach 1: Two Pass and TC - O(n) SC - O(n)
class Solution {

    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode curr = head;

        if (head == null || head.next == null)
            return true;

        while (curr != null) {
            stack.push(curr.val);
            curr = curr.next;
        }

        curr = head;

        while (curr != null) {
            if (stack.pop() != curr.val)
                return false;

            curr = curr.next;
        }

        return true;
    }
}

// Aproach 2: Fast & Slow Pointer + Reverse Second Half + Two Pointer Comparison
// TC - O(n) SC - O(1)
class Solution {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode fast = head;
        ListNode slow = head;

        // Finding the middle node
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // If odd length, skip middle
        if (fast != null) {
            slow = slow.next;
        }

        // Reverse second half
        ListNode prev = null;
        ListNode next = null;
        while (slow != null) {
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // Compare first half and reversed second half
        ListNode first = head;
        ListNode second = prev;

        while (second != null) {
            if (first.val != second.val)
                return false;
            first = first.next;
            second = second.next;
        }

        return true;
    }
}