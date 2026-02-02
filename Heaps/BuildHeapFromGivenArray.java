/* Given an array of integers nums, convert it in-place into a min-heap.
A binary min-heap is a complete binary tree where the key at the root is the minimum among all keys present in a binary min-heap and the same property is recursively true for all nodes in a Binary Tree.

Example 1
Input: nums = [6, 5, 2, 7, 1, 7]
Output: [1, 5, 2, 7, 6, 7]
Explanation: nums[0] <= nums[1], nums[2]
nums[1] <= nums[3], nums[4]
nums[2] <= nums[5]
*/
//TC - O(n) - buildHeap
//SC - O(logN)
class Solution {
    private void swap(int[] nums, int ind1, int ind2) {
        int temp = nums[ind1];
        nums[ind1] = nums[ind2];
        nums[ind2] = temp;
    }

    private void heapifyDown(int[] nums, int ind) {
        int smallestInd = ind;
        int n = nums.length;

        int lChild = 2 * ind + 1;
        int rChild = 2 * ind + 2;

        if (lChild < n && nums[lChild] < nums[smallestInd]) {
            smallestInd = lChild;
        }
        if (rChild < n && nums[rChild] < nums[smallestInd]) {
            smallestInd = rChild;
        }

        if (smallestInd != ind) {
            swap(nums, smallestInd, ind);
            heapifyDown(nums, smallestInd);
        }
    }

    public void buildMinHeap(int[] nums) {
        int n = nums.length;
        for (int ind = n / 2 - 1; ind >= 0; ind--) {
            heapifyDown(nums, ind);
        }

    }
}