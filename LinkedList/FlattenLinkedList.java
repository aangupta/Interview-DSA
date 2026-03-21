class Solution {

    // Function to merge two sorted bottom-linked lists
    private Node mergeList(Node list1, Node list2) {

        // Dummy node to simplify merging logic
        Node dummyNode = new Node(-1);
        Node res = dummyNode;

        // Merge both lists similar to merge step of merge sort
        while (list1 != null && list2 != null) {

            if (list1.data <= list2.data) {
                res.bottom = list1;

                res = res.bottom;
                list1 = list1.bottom;
            } else {
                res.bottom = list2;

                res = res.bottom;
                list2 = list2.bottom;
            }
        }

        // Attach remaining nodes if one list finishes earlier
        if (list1 != null)
            res.bottom = list1;
        else
            res.bottom = list2;

        // Return head of merged bottom list
        return dummyNode.bottom;
    }

    public Node flatten(Node head) {

        // Base case: empty list OR only one vertical list
        if (head == null || head.next == null)
            return head;

        // Recursively flatten the list on the right
        Node mergedHead = flatten(head.next);

        // Merge current list with already flattened right list
        return mergeList(head, mergedHead);
    }
}

/*
 * N = total nodes, K = number of vertical lists
 * Each merge takes O(N) in worst case.
 * Total merges ≈ K
 * TC ~ O(N * k)
 * SC ~O(k) recursive stack space
 */