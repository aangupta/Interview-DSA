/*
https://leetcode.com/problems/binary-tree-paths/description/

Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.

Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]

*/

class Solution {

    private void binaryTreePaths(TreeNode root, StringBuilder path, List<String> paths) {

        // Save current length to backtrack later
        int length = path.length();

        // If leaf node → complete path found
        if (root.left == null && root.right == null) {
            path.append(root.val); // add last node value
            paths.add(path.toString()); // store path
            path.setLength(length); // backtrack to previous state
            return;
        }

        // Add current node + arrow for further traversal
        path.append(root.val).append("->");

        // Recurse left
        if (root.left != null)
            binaryTreePaths(root.left, path, paths);

        // Recurse right
        if (root.right != null)
            binaryTreePaths(root.right, path, paths);

        // Backtrack: remove current node contribution
        path.setLength(length);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();

        if (root == null)
            return paths;

        StringBuilder path = new StringBuilder();

        // Start DFS traversal
        binaryTreePaths(root, path, paths);

        return paths;
    }
}