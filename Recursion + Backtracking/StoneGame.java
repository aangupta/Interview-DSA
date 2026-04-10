/*Problem: https://leetcode.com/problems/stone-game/description/
Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.

Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.

Example 1:

Input: piles = [5,3,4,5]
Output: true
Explanation: 
Alice starts first, and can only take the first 5 or the last 5.
Say she takes the first 5, so that the row becomes [3, 4, 5].
If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alice, so we return true.
Example 2:

Input: piles = [3,7,2,3]
Output: true

Pattern: Minimax Backtracking
*/

class Solution {
    private int dfs(int[] piles, int left, int right) {
        if (left > right)
            return 0;

        if (left == right)
            return piles[left];

        int pickLeft = piles[left] - dfs(piles, left + 1, right);
        int pickRight = piles[right] - dfs(piles, left, right - 1);

        return Math.max(pickLeft, pickRight);
    }

    public boolean stoneGame(int[] piles) {
        return dfs(piles, 0, piles.length - 1) >= 0 ? true : false;

    }
}

/* Memoization */
class Solution {
    private int dfs(int[] piles, int left, int right, int[][] dp) {
        if (left > right)
            return 0;

        if (left == right)
            return piles[left];

        if (dp[left][right] != -1)
            return dp[left][right];

        int pickLeft = piles[left] - dfs(piles, left + 1, right, dp);
        int pickRight = piles[right] - dfs(piles, left, right - 1, dp);

        return dp[left][right] = Math.max(pickLeft, pickRight);
    }

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for (int[] nums : dp) {
            Arrays.fill(nums, -1);
        }
        return dfs(piles, 0, piles.length - 1, dp) >= 0 ? true : false;

    }
}