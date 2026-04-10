/* https://leetcode.com/problems/binary-tree-preorder-traversal/description/

Given the root of a binary tree, return the preorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,2,3]

*/
//Recursive Approach
class Solution {

    private void preorderTraversal(TreeNode root, List<Integer> result) {

        if (root == null)
            return;
        result.add(root.val);

        preorderTraversal(root.left, result);
        preorderTraversal(root.right, result);

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null)
            return result;

        preorderTraversal(root, result);

        return result;

    }
}

// Iterative Approach
class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null)
            return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            stack.pop();
            result.add(node.val);

            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }

        return result;
    }
}
