/* https://leetcode.com/problems/sort-list/description/
Given the head of a linked list, return the list after sorting it in ascending order.
*/

/* Approach 1 : 
* Traverse LL and store all elements in an array. 
* Sort the array.
* Traverse the LL again and update the LL value with array value
* TC - O(N), SC - O(N)
*/

class Solution {
    private void swapNodes(ListNode node1, ListNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        boolean changesMade = true;

        while (true) {
            changesMade = false;
            ListNode temp = head;
            while (temp.next != null) {
                if (temp.val > temp.next.val) {
                    changesMade = true;
                    swapNodes(temp, temp.next);
                }
                temp = temp.next;
            }
            // If no swaps occurred during the pass, the list is sorted.
            if (changesMade == false)
                break; // imp lien
        }

        return head;

    }
}

// Approach 2: Merge Sort
class Solution {

    // Function to merge two sorted linked lists
    private ListNode merge(ListNode head1, ListNode head2) {

        // Dummy node to simplify merging logic
        ListNode dummy = new ListNode(-1);

        // Pointer to build the merged list
        ListNode temp = dummy;

        // Traverse both lists and attach the smaller node
        while (head1 != null && head2 != null) {

            if (head1.val < head2.val) {

                // Attach node from first list
                temp.next = head1;

                // Move temp pointer forward
                temp = head1;

                // Move head1 forward
                head1 = head1.next;

            } else {

                // Attach node from second list
                temp.next = head2;

                // Move temp pointer forward
                temp = head2;

                // Move head2 forward
                head2 = head2.next;
            }
        }

        // Attach remaining nodes if any list is not finished
        if (head1 != null)
            temp.next = head1;
        else
            temp.next = head2;

        // Return merged sorted list
        return dummy.next;
    }

    // Function to find the middle node of the linked list
    private ListNode findMiddle(ListNode head) {

        // Slow and fast pointer technique
        ListNode slow = head;
        ListNode fast = head.next;

        // When fast reaches end, slow will be at middle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Return middle node
        return slow;
    }

    public ListNode sortList(ListNode head) {

        // Base case: empty list OR single node is already sorted
        if (head == null || head.next == null)
            return head;

        // Find middle node to split the list
        ListNode middleNode = findMiddle(head);

        // Left half starts from head
        ListNode leftHead = head;

        // Right half starts after middle
        ListNode rightHead = middleNode.next;

        // Break the list into two halves
        middleNode.next = null;

        // Recursively sort left half
        leftHead = sortList(leftHead);

        // Recursively sort right half
        rightHead = sortList(rightHead);

        // Merge the two sorted halves
        return merge(leftHead, rightHead);
    }
}

/*
 * Finding middle → O(n)
 * Recursively splitting list → log n levels
 * Merging lists → O(n)
 * TC - O(nlogn)
 * SC - O(logn) - recursive stack space
 */