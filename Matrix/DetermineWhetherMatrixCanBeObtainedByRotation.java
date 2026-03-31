/*https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/?envType=daily-question&envId=2026-03-22
Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.

Example 1:
Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.
*/

//Approach (Rotate and check till 4 rotations)
//T.C : O(n^2)
//S.C : O(1)
class Solution {
    int n;

    private void rotate(int[][] mat) {
        // transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        // reverse row-wise
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = mat[i][left];
                mat[i][left] = mat[i][right];
                mat[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        n = mat.length;

        for (int c = 1; c <= 4; c++) {
            boolean equal = true;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] != target[i][j]) {
                        equal = false;
                        break;
                    }
                }
                if (equal == false)
                    break;
            }

            if (equal == true)
                return true;
            rotate(mat);
        }
        return false;
    }
}