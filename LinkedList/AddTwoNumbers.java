/*https://takeuforward.org/plus/dsa/problems/add-two-numbers-in-ll?category=linked-list&subcategory=logic-building

Given two non-empty linked lists linkedList1 and linkedList2 which represent two non-negative integers.
The digits are stored in reverse order with each node storing one digit.
Add two numbers and return the sum as a linked list.
The sum Linked List will be in reverse order as well.
The Two given Linked Lists represent numbers without any leading zeros, except when the number is zero itself.

Example 1
Input: linkedList1 = [5, 4], linkedList2 = [4]
Output: [9, 4]
Explanation: linkedList1 = 45, linkedList2 = 4.
linkedList1 + linkedList2 = 45 + 4 = 49.
*/

//TC - O(max(m,n)) m,n - size of given list l1 and l2
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // YOUR CODE GOES HERE
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        int carry = 0;

        while (l1 != null || l2 != null) {
            int num1 = (l1 == null ? 0 : l1.data);
            int num2 = (l2 == null ? 0 : l2.data);

            int sum = (num1 + num2 + carry);
            tail.next = new ListNode(sum % 10);
            tail = tail.next;

            carry = sum / 10;

            // When using || in the loop condition, one list may be null.
            // Accessing .next on null → Runtime Error (NullPointerException).
            // Always null-check before updating pointers.
            /*
             * l1 = l1.next;
             * l2 = l2.next;
             */

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }

        if (carry > 0) {
            tail.next = new ListNode(carry);
        }

        return dummy.next;
    }
}