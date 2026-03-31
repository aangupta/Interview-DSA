/*
https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/?envType=daily-question&envId=2026-03-23

You are given a m x n matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step, you can only move right or down in the matrix.

Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (m - 1, n - 1), find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid cells visited along the path.

Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative, return -1.

Notice that the modulo is performed after getting the maximum product.

 

Example 1:


Input: grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
Output: -1
Explanation: It is not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.

*/

class Pair {
    long max;
    long min;

    Pair(long max, long min) {
        this.max = max;
        this.min = min;
    }
}

class Solution {
    int m, n;
    final int MOD = 1000000007;
    Pair[][] dp;

    private Pair solve(int[][] grid, int i, int j) {
        // base case
        if (i == m - 1 && j == n - 1) {
            return new Pair(grid[i][j], grid[i][j]);
        }

        if (dp[i][j] != null)
            return dp[i][j];

        long maxVal = Long.MIN_VALUE;
        long minVal = Long.MAX_VALUE;

        // down
        if (i + 1 < m) {
            Pair down = solve(grid, i + 1, j);
            long a = grid[i][j] * down.max;
            long b = grid[i][j] * down.min;

            maxVal = Math.max(maxVal, Math.max(a, b));
            minVal = Math.min(minVal, Math.min(a, b));
        }

        // right
        if (j + 1 < n) {
            Pair down = solve(grid, i, j + 1);
            long a = grid[i][j] * down.max;
            long b = grid[i][j] * down.min;

            maxVal = Math.max(maxVal, Math.max(a, b));
            minVal = Math.min(minVal, Math.min(a, b));
        }

        dp[i][j] = new Pair(maxVal, minVal);
        return dp[i][j];
    }

    public int maxProductPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        dp = new Pair[m][n];

        Pair result = solve(grid, 0, 0);
        return result.max < 0 ? -1 : (int) (result.max % MOD);
    }
}
