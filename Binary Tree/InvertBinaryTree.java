/*
https://leetcode.com/problems/invert-binary-tree/description/

Given the root of a binary tree, invert the tree, and return its root.

Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
*/

class Solution {
    public TreeNode invertTree(TreeNode root) {

        // Base case: empty node
        if (root == null)
            return root;

        // Recursively invert left subtree
        invertTree(root.left);

        // Recursively invert right subtree
        invertTree(root.right);

        // Swap left and right children of current node
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Return root of inverted subtree
        return root;
    }
}