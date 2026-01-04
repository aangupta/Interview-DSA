/*Problem: https://leetcode.com/problems/permutations/description/

Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]

Pattern: Backtracking
*/

/*
* TC - O(n! * n)
* SC - O(n) + O(n) recursive stack space + freq array
 */
class Solution {

    private void backtrack(int[] nums, boolean[] freq, List<Integer> curr, List<List<Integer>> result) {
        if (curr.size() == nums.length) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (freq[i] == false) {
                curr.add(nums[i]);
                freq[i] = true;
                backtrack(nums, freq, curr, result);
                curr.remove(curr.size() - 1);
                freq[i] = false;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        boolean[] freq = new boolean[nums.length];

        backtrack(nums, freq, curr, result);

        return result;
    }
}

/*
 * Space Optimised Solution
 * TC - O(n! * n)
 * SC - O(n) recursive stack space
 */
class Solution {

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void backtrack(int index, int[] nums, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int num : nums) {
                temp.add(num);
            }
            result.add(temp);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(i, index, nums);
            backtrack(index + 1, nums, result);
            swap(i, index, nums);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, result);

        return result;
    }
}
