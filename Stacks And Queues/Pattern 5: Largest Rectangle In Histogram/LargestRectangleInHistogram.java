/*
https://leetcode.com/problems/largest-rectangle-in-histogram/

Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
*/

//TC - O(n * m)
class Solution {

    private int[] findPrevSmaller(int[] heights, int n) {
        int[] pse = new int[n]; // stores index of previous smaller element
        Stack<Integer> stack = new Stack<>(); // monotonic increasing stack (indices)

        for (int i = 0; i < n; i++) {
            // pop until we find strictly smaller element
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            // if empty → no smaller on left → boundary = -1
            // else → top is previous smaller index
            pse[i] = (stack.isEmpty() ? -1 : stack.peek());

            // push current index for future elements
            stack.push(i);
        }

        return pse;
    }

    private int[] findNextSmaller(int[] heights, int n) {
        int[] nse = new int[n]; // stores index of next smaller element
        Stack<Integer> stack = new Stack<>(); // monotonic increasing stack

        for (int i = n - 1; i >= 0; i--) {
            // pop until we find strictly smaller element
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            // if empty → no smaller on right → boundary = n
            // else → top is next smaller index
            nse[i] = (stack.isEmpty() ? n : stack.peek());

            // push current index
            stack.push(i);
        }
        return nse;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        // get boundaries where current bar can expand
        int[] nse = findNextSmaller(heights, n);
        int[] pse = findPrevSmaller(heights, n);

        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            // width = stretch between smaller elements (exclusive)
            int width = nse[i] - pse[i] - 1;

            // current bar acts as height
            int area = heights[i] * width;

            // maximize area
            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }
}
