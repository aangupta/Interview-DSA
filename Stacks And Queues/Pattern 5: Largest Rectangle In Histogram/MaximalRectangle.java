/*
https://leetcode.com/problems/maximal-rectangle/

Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example 1:
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 */

//Extension of Largest Rectangle in Histogram Problem
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

    private int largestRectangleArea(int[] heights) {
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

    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int maxArea = Integer.MIN_VALUE;

        int[] heights = new int[m]; // histogram for current row

        // build histogram for first row
        for (int j = 0; j < m; j++) {
            heights[j] = matrix[0][j] - '0';
        }

        // compute max rectangle for first row histogram
        maxArea = largestRectangleArea(heights);

        // process remaining rows
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // if '1' → extend height (continuous vertical)
                if (matrix[i][j] == '1') {
                    heights[j] += matrix[i][j] - '0';
                }
                // if '0' → break → reset height
                else {
                    heights[j] = 0;
                }
            }

            // treat updated heights as histogram and compute area
            int currArea = largestRectangleArea(heights);

            // keep track of global maximum
            maxArea = Math.max(maxArea, currArea);
        }

        return maxArea;
    }
}

/*
 * Convert each row into a histogram of consecutive 1s (vertical height).
 * For every row, solve Largest Rectangle in Histogram.
 * Track the maximum area across all rows.
 */
