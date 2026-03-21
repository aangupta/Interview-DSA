/* https://leetcode.com/problems/count-submatrices-with-equal-frequency-of-x-and-y/description/?envType=daily-question&envId=2026-03-19

Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of submatrices that contain:

grid[0][0]
an equal frequency of 'X' and 'Y'.
at least one 'X'.
 

Example 1:

Input: grid = [["X","Y","."],["Y",".","."]]

Output: 3

 */

//Using Submatrics prefix sum concept
class Solution {

    public int numberOfSubmatrices(char[][] grid) {
        int count = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        // Prefix sum matrices:
        // cumSumX[r][c] → number of 'X' from (0,0) to (r,c)
        // cumSumY[r][c] → number of 'Y' from (0,0) to (r,c)
        int[][] cumSumX = new int[rows][cols];
        int[][] cumSumY = new int[rows][cols];

        // Traverse each cell → treat (r,c) as bottom-right of submatrix (0,0) to (r,c)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // Initialize current cell contribution
                // Add 1 if current cell contains 'X' or 'Y'
                cumSumX[r][c] = (grid[r][c] == 'X') ? 1 : 0;
                cumSumY[r][c] = (grid[r][c] == 'Y') ? 1 : 0;

                // Add contribution from top cell (if exists)
                if (r > 0) {
                    cumSumX[r][c] += cumSumX[r - 1][c];
                    cumSumY[r][c] += cumSumY[r - 1][c];
                }

                // Add contribution from left cell (if exists)
                if (c > 0) {
                    cumSumX[r][c] += cumSumX[r][c - 1];
                    cumSumY[r][c] += cumSumY[r][c - 1];
                }

                // Subtract overlapping top-left region (to avoid double counting)
                if (r > 0 && c > 0) {
                    cumSumX[r][c] -= cumSumX[r - 1][c - 1];
                    cumSumY[r][c] -= cumSumY[r - 1][c - 1];
                }

                // Now:
                // cumSumX[r][c] = total 'X' in submatrix (0,0) → (r,c)
                // cumSumY[r][c] = total 'Y' in submatrix (0,0) → (r,c)

                // Check condition:
                // number of 'X' == number of 'Y' AND at least one exists
                if (cumSumX[r][c] == cumSumY[r][c] && cumSumX[r][c] > 0) {
                    count++;
                }
            }
        }

        return count;
    }
}