/* Problem Link: https://leetcode.com/problems/subarrays-with-k-different-integers/description/ 
Given an integer array nums and an integer k, return the number of good subarrays of nums.

A good array is an array where the number of different integers in that array is exactly k.

For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.

Example 1:

Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
Example 2:

Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 

Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i], k <= nums.length

Pattern : Sliding Window (Exactly K)
*/
// TC: O(n) (each element enters and leaves window once)
// Space: O(k) (frequency map)
class Solution {

    private int atMost(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int count = 0;
        int left = 0, right = 0;

        while (right < nums.length) {
            int num = nums[right];
            freq.put(num, freq.getOrDefault(num, 0) + 1);

            // valid window condition voilated
            while (freq.size() > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);

                if (freq.get(nums[left]) == 0) {
                    freq.remove(nums[left]);
                }

                left++;
            }
            // valid window
            count += (right - left + 1);
            right++;
        }
        return count;
    }

    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }
}
