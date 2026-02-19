
//Approach 1 TC: O(nLogn)
class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null;
        ListNode tail = null;

        if (lists.length == 0)
            return head;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        for (int i = 0; i < lists.length; i++) {
            ListNode temp = lists[i];
            while (temp != null) {
                minHeap.add(temp);
                temp = temp.next;
            }
        }

        while (minHeap.size() > 0) {
            ListNode curr = minHeap.poll();

            if (head == null) {
                head = new ListNode(curr.val);
                tail = head;
            } else {
                tail.next = new ListNode(curr.val);
                tail = tail.next;
            }
        }

        return head;
    }
}

// Approach 2: Optimised TC O(nlogk) SC O(k) k = size of the lists
/*
 * Extract the smallest node.
 * Add it to result.
 * Push its next node into heap.
 * So heap size is always at most k (not N).
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || lists == null)
            return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        // adding the first node of all the list in min heap
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        while (minHeap.size() > 0) {
            ListNode curr = minHeap.poll();
            tail.next = curr;
            tail = tail.next;

            if (curr.next != null) {
                minHeap.add(curr.next);
            }
        }

        return dummy.next;
    }
}
