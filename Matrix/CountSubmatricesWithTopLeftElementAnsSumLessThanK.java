/*
https://leetcode.com/problems/count-submatrices-with-top-left-element-and-sum-less-than-k/description/?envType=daily-question&envId=2026-03-18

You are given a 0-indexed integer matrix grid and an integer k.

Return the number of submatrices that contain the top-left element of the grid, and have a sum less than or equal to k.

Example 1:
Input: grid = [[7,6,3],[6,6,1]], k = 18
Output: 4
Explanation: There are only 4 submatrices, shown in the image above, that contain the top-left element of grid, and have a sum less than or equal to 18.

*/
//TC - O(m*n*m*n) - TLE
class Solution {

    public int countSubmatrices(int[][] grid, int k) {
        int count = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        // Iterate over all bottom-right corners
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                int sum = 0;

                // Calculate sum of submatrix (0,0) to (r,c)
                for (int i = 0; i <= r; i++) {
                    for (int j = 0; j <= c; j++) {
                        sum += grid[i][j];
                    }
                }

                // Check AFTER full submatrix sum is computed
                if (sum <= k) {
                    count++;
                }
            }
        }

        return count;
    }
}

// Optimal Approach (Using Prefix Sum)
// TC: O(m*n) SC: O(1)
class Solution {

    public int countSubmatrices(int[][] grid, int k) {
        int count = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        // Traverse each cell → treat it as bottom-right corner of submatrix (0,0) to
        // (r,c)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // Add sum from top cell (if exists)
                if (r > 0) {
                    grid[r][c] += grid[r - 1][c];
                }

                // Add sum from left cell (if exists)
                if (c > 0) {
                    grid[r][c] += grid[r][c - 1];
                }

                // Subtract overlapping top-left region (to avoid double counting)
                if (r > 0 && c > 0) {
                    grid[r][c] -= grid[r - 1][c - 1];
                }

                // Now grid[r][c] stores sum of submatrix from (0,0) to (r,c)

                // Check if this submatrix sum satisfies condition
                if (grid[r][c] <= k) {
                    count++;
                }
            }
        }

        return count;
    }
}