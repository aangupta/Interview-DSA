/*Problem: https://leetcode.com/problems/binary-subarrays-with-sum/

Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.

A subarray is a contiguous part of the array.

Example 1:

Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
Example 2:

Input: nums = [0,0,0,0,0], goal = 0
Output: 15
Pattern: Sliding Window , PrefixSum + HashMap

Time Complexity: O(n)
Space Complexity: O(1)
*/

/* Sliding Window Approach */
class Solution {

    private int atMost(int[] nums, int goal) {
        if (goal < 0)
            return 0;

        int left = 0, right = 0;
        int sum = 0;
        int count = 0;

        while (right < nums.length) {
            sum += nums[right];

            while (sum > goal) {
                sum -= nums[left];
                left++;
            }

            count += right - left + 1;
            right++;
        }
        return count;
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }
}

/*
 * Solution -2 (PrefixSum + HashMap)
 */
class Solution {

    public int numSubarraysWithSum(int[] nums, int goal) {
        // stores sum and its frequency
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // handles subarrays that start at index 0

        int count = 0;
        int sum = 0;

        for (int num : nums) {
            sum += num;

            if (map.containsKey(sum - goal)) {
                count += map.get(sum - goal);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}

/*
 * Notes
 * Counting subarrays with exact sum ≠ sliding window directly when zeros exist
 * Use prefix sum or atMost(goal) − atMost(goal−1)
 * 
 * Sliding window works when:
 * expanding window monotonically increases sum
 * shrinking window monotonically decreases sum
 * and only one valid window per right pointer
 * Here:
 * zeros allow multiple valid left positions
 * multiple subarrays can end at same right
 * So: Direct sliding window → undercounts
 */
