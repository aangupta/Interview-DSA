
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