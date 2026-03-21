/* https://leetcode.com/problems/minimum-absolute-difference-in-sliding-submatrix/?envType=daily-question&envId=2026-03-20
*/

//Do what problem says

class Solution {

    // Helper function to compute minimum absolute difference
    // in a k x k subgrid starting at (row, col)
    private int findMinAbsDiff(int[][] grid, int row, int col, int k) {

        // Step 1: Flatten k x k subgrid into 1D array
        int[] arr = new int[k * k];
        int indx = 0;

        for (int i = row; i < row + k; i++) {
            for (int j = col; j < col + k; j++) {
                arr[indx++] = grid[i][j];
            }
        }

        // Step 2: Sort array to bring closest elements together
        Arrays.sort(arr);

        // Step 3: Find minimum difference between adjacent UNIQUE elements
        int minDiff = Integer.MAX_VALUE;

        for (int i = 1; i < k * k; i++) {

            // Ignore duplicates because diff = 0 is not allowed here
            if (arr[i] != arr[i - 1]) {
                minDiff = Math.min(minDiff, Math.abs(arr[i] - arr[i - 1]));
            }
        }

        // Step 4: If no valid pair found (all elements same), return 0
        return (minDiff == Integer.MAX_VALUE ? 0 : minDiff);
    }

    public int[][] minAbsDiff(int[][] grid, int k) {

        int rows = grid.length;
        int cols = grid[0].length;

        // Result matrix size = number of possible k x k windows
        int[][] ans = new int[rows - k + 1][cols - k + 1];

        // Edge case: k = 1 → only one element → no pair → diff = 0
        if (k == 1)
            return ans;

        // Step 5: Traverse all possible k x k subgrids
        for (int i = 0; i < rows - k + 1; i++) {
            for (int j = 0; j < cols - k + 1; j++) {

                // Compute answer for each subgrid
                ans[i][j] = findMinAbsDiff(grid, i, j, k);
            }
        }

        return ans;
    }
}