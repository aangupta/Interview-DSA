/*https://takeuforward.org/plus/dsa/problems/sort-a-ll-of-0's-1's-and-2's?category=linked-list&subcategory=logic-building&tab=description&approach=optimal
Given the head of a singly linked list consisting of only 0, 1 or 2.
Sort the given linked list and return the head of the modified list.
Do it in-place by changing the links between the nodes without creating new nodes.

Example 1
Input: linkedList = [1, 0, 2, 0 , 1]
Output: [0, 0, 1, 1, 2]
Explanation: The values after sorting are [0, 0, 1, 1, 2].

*/

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode zeroHead = new ListNode(-1);
        ListNode oneHead = new ListNode(-1);
        ListNode twoHead = new ListNode(-1);

        ListNode zero = zeroHead;
        ListNode one = oneHead;
        ListNode two = twoHead;

        ListNode curr = head;

        while (curr != null) {
            if (curr.data == 0) {
                zero.next = curr;
                zero = zero.next;
            } else if (curr.data == 1) {
                one.next = curr;
                one = one.next;
            } else {
                two.next = curr;
                two = two.next;
            }
            curr = curr.next;
        }

        zero.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        one.next = twoHead.next;
        two.next = null;

        return zeroHead.next;
    }
}