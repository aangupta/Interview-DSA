/* https://leetcode.com/problems/middle-of-the-linked-list/description/

Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.

Example 1:

Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.

Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.

*/
//Approach 1: (Two Pass)
class Solution {
    private int countNodes(ListNode curr) {
        int count = 0;

        while (curr != null) {
            count++;
            curr = curr.next;
        }

        return count;
    }

    public ListNode middleOfLinkedList(ListNode head) {
        int nodes = countNodes(head);

        ListNode curr = head;

        int mid = (nodes / 2);

        for (int i = 0; i < mid; i++) {
            curr = curr.next;
        }

        return curr;

    }
}

// Approach 2: SLow + Faster Technique (One Pass)
class Solution {
    public ListNode middleOfLinkedList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;

    }
}
// Fast moves twice the speed of slow. When fast reaches the end of the list,
// slow has traversed half the distance, which positions it at the middle.”