/*
https://leetcode.com/problems/maximum-depth-of-binary-tree/

Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Input: root = [3,9,20,null,null,15,7]
Output: 3
*/

//TC - O(n)   SC -O(n)-worst case
class Solution {
    public int maxDepth(TreeNode root) {

        // Base case: no node contributes 0 depth
        if (root == null)
            return 0;

        // Get depth of left subtree
        int leftDepth = maxDepth(root.left);

        // Get depth of right subtree
        int rightDepth = maxDepth(root.right);

        // Current node depth = 1 (itself) + max of left & right subtree depths
        return 1 + Math.max(leftDepth, rightDepth);
    }
}