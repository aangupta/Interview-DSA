/*
https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/

Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.

Example 1:

Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.*/

//TC - O(n)   SC - O(n) worst case  O(logn) - best case
class Solution {

    private int goodNodes(TreeNode root, int max) {

        // Base case: no node → no contribution
        if (root == null)
            return 0;

        // Leaf node: check if it is a "good node"
        if (root.left == null && root.right == null) {
            return (root.val >= max ? 1 : 0);
        }

        int count = 0;

        // If current node value ≥ max seen so far → it's a good node
        if (root.val >= max)
            count++;

        // Update max for children and recurse
        count += goodNodes(root.left, Math.max(max, root.val));
        count += goodNodes(root.right, Math.max(max, root.val));

        return count;
    }

    public int goodNodes(TreeNode root) {

        // Start with root value as initial max
        int max = root.val;

        return goodNodes(root, max);
    }
}