/*
https://leetcode.com/problems/trapping-rain-water/description/
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 */

//In this problem for every index i - we need to find the max element in left half (0......i) and max element in right half(i ... n-1)
class Solution {
    public int trap(int[] height) {
        int n = height.length;

        int[] leftMax = new int[n]; // max height from left till i
        int[] rightMax = new int[n]; // max height from right till i

        // build leftMax → highest bar seen so far from left
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // build rightMax → highest bar seen so far from right
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int water = 0;

        // water at each index = min(leftMax, rightMax) - height
        for (int i = 0; i < n; i++) {
            water += (Math.min(leftMax[i], rightMax[i]) - height[i]);
        }

        return water;
    }
}