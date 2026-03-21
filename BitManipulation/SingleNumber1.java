/*
Given an array of nums of n integers. Every integer in the array appears twice except one integer. Find the number that appeared once in the array.

Example 1
Input : nums = [1, 2, 2, 4, 3, 1, 4]
Output : 3

Example 2
Input : nums = [5]
Output : 5
*/

class Solution {
    public int singleNumber(int[] nums) {
        // your code goes here
        int ans = 0;

        for (int num : nums) {
            ans = ans ^ num;
        }

        return ans;
    }
}