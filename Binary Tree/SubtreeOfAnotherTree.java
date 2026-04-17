/*
https://leetcode.com/problems/subtree-of-another-tree/description/

Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.

Example 1:
Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true

Example 2:
Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false

*/

// Time:  O(n * m)
// Space: O(n) worst case (recursion stack)
class Solution {

    private boolean isIdentical(TreeNode root1, TreeNode root2) {
        // case 1: both null → identical
        if (root1 == null && root2 == null)
            return true;

        // case 2: one null → not identical
        if (root1 == null || root2 == null)
            return false;

        // case 3: values match + check subtrees
        return root1.val == root2.val &&
                isIdentical(root1.left, root2.left) &&
                isIdentical(root1.right, root2.right);
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // edge case
        if (root == null)
            return false;

        // check current node
        if (isIdentical(root, subRoot))
            return true;

        // check left or right
        return isSubtree(root.left, subRoot) ||
                isSubtree(root.right, subRoot);
    }
}

/**
 * MISTAKE - Earlier
 * Use findRoot() → find first occurrence of subRoot.val in root
 * Then check isIdentical() only for that node
 * Problem: You are checking only ONE candidate, but there can be multiple
 * possible matches.
 */