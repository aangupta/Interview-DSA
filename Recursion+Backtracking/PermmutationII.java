/*Problem: https://leetcode.com/problems/permutations-ii/description/

Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
Example 2:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Pattern: Backtracking

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
        Set<Integer> used = new HashSet<>();

        for (int i = index; i < nums.length; i++) {
            // skipping duplicate elements
            if (used.contains(nums[i]))
                continue;

            used.add(nums[i]);
            swap(i, index, nums);
            backtrack(index + 1, nums, result);
            swap(i, index, nums);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, result);
        return result;
    }
}
/*
 * MISTAKE
 * for skipping duplicate added the below condition in for loop
 * (i > index && nums[i] == nums[index]) continue
 * because I am doing swap-based backtracking and modifying nums in-place
 * After the first swap, nums[index] no longer represents the original value at
 * that position.
 */
