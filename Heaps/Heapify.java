/* Given an array nums representing a min-heap and two integers ind and val, set the value at index ind (0-based) to val and perform the heapify algorithm such that the resulting array follows the min-heap propert
Mdify the original array in-place, no need to return anything
Example 1
Input: nums = [1, 4, 5, 5, 7, 6], ind = 5, val = 2
Output: [1, 4, 2, 5, 7, 5]
Explanation: After setting index 5 to 2, the resulting heap in array form = [1, 4, 5, 5, 7, 2]

Parent of nums[5] = nums[2] = 5 > nums[5] = 2, so they are swapped.

Final array = [1, 4, 2, 5, 7, 5]
 
*/
// TC - O(logN) - height of the heap
// SC - O(logN) - recursive stack space
/*
* Heap Update (Min Heap)
* If newVal < oldVal → heapifyUp
* If newVal > oldVal → heapifyDown
*/
class Solution {
    public void heapify(int[] nums, int ind, int val) {
        if (nums[ind] > val) {
            nums[ind] = val;
            heapifyUp(nums, ind);
        } else {
            nums[ind] = val;
            heapifyDown(nums, ind);
        }

    }

    private void swap(int[] nums, int ind1, int ind2) {
        int temp = nums[ind1];
        nums[ind1] = nums[ind2];
        nums[ind2] = temp;
    }

    private void heapifyUp(int[] nums, int ind) {
        int parent = (ind - 1) / 2;

        if (ind > 0 && nums[ind] < nums[parent]) {
            swap(nums, ind, parent);
            heapifyUp(nums, parent);
        }

    }

    private void heapifyDown(int[] nums, int ind) {
        int smallestInd = ind;
        int lChild = 2 * ind + 1;
        int rChild = 2 * ind + 2;
        int n = nums.length;

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
}
