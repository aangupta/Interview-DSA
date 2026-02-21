/* https://takeuforward.org/plus/dsa/problems/add-one-to-a-number-represented-by-ll?category=linked-list&subcategory=faqs--medium&tab=description
Given the head of a singly linked list representing a positive integer number. Each node of the linked list represents a digit of the number, with the 1st node containing the leftmost digit of the number and so on. The task is to add one to the value represented by the linked list and return the head of a linked list containing the final value.

The number will contain no leading zeroes except when the value represented is zero itself.

Example 1

Input: head -> 1 -> 2 -> 3

Output: head -> 1 -> 2 -> 4

Explanation: The number represented by the linked list = 123.

123 + 1 = 124.

Example 2

Input: head -> 9 -> 9

Output: head -> 1 -> 0 -> 0

Explanation: The number represented by the linked list = 99.

99 + 1 = 100.
*/

//Approach 1: Using reverse + Two Pass
class Solution {

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode front = curr.next;
            curr.next = prev;
            prev = curr;
            curr = front;
        }
        return prev;
    }

    public ListNode addOne(ListNode head) {

        head = reverse(head);

        ListNode curr = head;
        ListNode prev = null;

        int carry = 1; // because we are adding 1

        while (curr != null && carry > 0) {

            int sum = curr.val + carry;
            curr.val = sum % 10;
            carry = sum / 10;

            prev = curr;
            curr = curr.next;
        }

        // If carry still remains, add new node
        if (carry > 0) {
            prev.next = new ListNode(carry);
        }

        return reverse(head);
    }
}

// Approach 2: without reverse + One Pass (more optimise)
class Solution {
    public ListNode addOne(ListNode head) {
        // Create dummy in case all digits are 9
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode lastNonNine = dummy;
        ListNode curr = head;

        // Step 1: Find rightmost non-9 node
        while (curr != null) {
            if (curr.val != 9) {
                lastNonNine = curr;
            }
            curr = curr.next;
        }

        // Step 2: Increment that node
        lastNonNine.val++;
        curr = lastNonNine.next;

        // Step 3: Make all nodes after it zero
        while (curr != null) {
            curr.val = 0;
            curr = curr.next;
        }

        // If dummy was incremented, return dummy
        if (dummy.val == 1) {
            return dummy;
        }

        return head;

    }
}