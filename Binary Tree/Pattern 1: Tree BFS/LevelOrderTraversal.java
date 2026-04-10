/*
https://leetcode.com/problems/binary-tree-level-order-traversal/description/

Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]

*/

class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        // stores final level-wise result
        List<List<Integer>> result = new ArrayList<>();

        // edge case: empty tree
        if (root == null)
            return result;

        // queue for BFS traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root); // start with root

        // process until all nodes are visited
        while (queue.size() > 0) {

            int size = queue.size(); // number of nodes at current level
            List<Integer> currLevel = new ArrayList<>();

            // process all nodes of current level
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove(); // dequeue node
                currLevel.add(node.val); // add value to current level

                // add children for next level
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }

            // add current level to result
            result.add(currLevel);
        }

        return result;
    }
}

/*
 * Time Complexity: O(N)
 * - Each node is visited exactly once.
 * 
 * Space Complexity: O(N)
 * - Queue stores up to N nodes in worst case (last level of tree).
 * - Result list also stores all N nodes.
 */