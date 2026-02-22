/*
https://leetcode.com/problems/intersection-of-two-linked-lists/description/
Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
*/

//Approach 1: Length Difference Method TC - O(n) SC -O(1)
public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        // If either list is empty, no intersection possible
        if (headA == null || headB == null)
            return null;

        ListNode currA = headA;
        ListNode currB = headB;

        // Calculate length of List A
        int lengthA = 0;
        while (currA != null) {
            lengthA++;
            currA = currA.next;
        }

        // Calculate length of List B
        int lengthB = 0;
        while (currB != null) {
            lengthB++;
            currB = currB.next;
        }

        // Reset pointers back to heads
        currA = headA;
        currB = headB;

        // Align both lists to same starting point
        // Move pointer of longer list ahead by length difference
        if (lengthA > lengthB) {
            int diff = lengthA - lengthB;
            while (diff-- > 0) {
                currA = currA.next;
            }
        } else {
            int diff = lengthB - lengthA;
            while (diff-- > 0) {
                currB = currB.next;
            }
        }

        // Traverse both lists together
        // Compare node reference (NOT node value)
        while (currA != null && currB != null) {
            if (currA == currB) { // reference comparison
                return currA; // intersection found
            }
            currA = currA.next;
            currB = currB.next;
        }

        // No intersection
        return null;
    }
}

// Approach 2: Two Pointer Traversal + Head Switching TC - O(n) SC -O(1)
public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode currA = headA;
        ListNode currB = headB;

        while (currA != currB) {
            currA = (currA == null) ? headB : currA.next;
            currB = (currB == null) ? headA : currB.next;
        }

        return currA; // either intersection node or null
    }
}
