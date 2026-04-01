/*
https://leetcode.com/problems/subsets/

Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.

*/

class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        int total = 1 << n; // 2^n

        List<List<Integer>> result = new ArrayList<>();

        // Loop through all possible bitmasks
        for (int i = 0; i < total; i++) {
            List<Integer> subset = new ArrayList<>();

            // Check each bit
            for (int j = 0; j < n; j++) {
                // If j-th bit is set → include nums[j]
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }

            result.add(subset);
        }

        return result;
    }
}
