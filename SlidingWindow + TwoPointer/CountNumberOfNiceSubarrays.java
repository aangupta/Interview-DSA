/* Problem Link: https://leetcode.com/problems/count-number-of-nice-subarrays/description/
Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16

Pattern : Sliding Window (Exact K patterns) and Prefix Sum + hashmap
*/

//TC - O(n) , SC O(n)
/*
* prefixSum[j] − prefixSum[i] = k
* Then subarray (i+1 … j) has exactly k odd numbers.
* So we store how many times a prefix sum has appeared.
*/
class Solution {

    public int numberOfSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // always remember
        int count = 0;
        int prefixSum = 0;

        for (int num : nums) {
            if (num % 2 == 1)
                prefixSum += 1;

            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }

            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}

// TC - O(n), SC - O(1)
class Solution {

    private int atMost(int[] nums, int k) {
        int oddCount = 0;
        int left = 0;
        int right = 0;
        int count = 0;

        while (right < nums.length) {
            int num = nums[right];

            if (num % 2 == 1)
                oddCount++;

            while (oddCount > k) {
                if (nums[left] % 2 == 1)
                    oddCount--;

                left++;
            }

            count += right - left + 1;
            right++;
        }
        return count;
    }

    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }
}