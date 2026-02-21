/*
https://leetcode.com/problems/odd-even-linked-list/description/
Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in O(1) extra space complexity and O(n) time complexity.

Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]

*/
//Approach 1: TC - O(n) SC O(n)
class Solution {
    public ListNode oddEvenList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode odd = dummy;
        ListNode dummy2 = new ListNode(-1);
        ListNode even = dummy2;

        int index = 1;
        ListNode curr = head;

        while (curr != null) {
            if (index % 2 == 1) {
                odd.next = new ListNode(curr.data);
                odd = odd.next;
            } else {
                even.next = new ListNode(curr.data);
                even = even.next;
            }
            curr = curr.next;
            index++;
        }
        odd.next = dummy2.next;
        return dummy.next;
    }
}

// Approach 2: TC - O(n) SC - O(1)

class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }
}