/*
Given an array of integers nums. Check whether the array represents a binary min-heap or not. Return true if it does, otherwise return false.
A binary min-heap is a complete binary tree where the key at the root is the minimum among all keys present in a binary min-heap and the same property is recursively true for all nodes in a Binary Tree.
Input: nums = [10, 20, 30, 21, 23]
Output: true
Explanation: Each node has a lower or equal value than its children. 
*/

//TC - O(n/2) ~ O(n)
//SC - O(1)
class Solution {
    public boolean isHeap(int[] nums) {
        int n = nums.length;

        for (int ind = n / 2 - 1; ind >= 0; ind--) {
            int lchild = 2 * ind + 1;
            int rchild = 2 * ind + 2;

            if (lchild < n && nums[lchild] < nums[ind])
                return false;
            if (rchild < n && nums[rchild] < nums[ind])
                return false;
        }

        return true;

    }
}