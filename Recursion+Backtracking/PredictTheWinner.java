/*Problem: https://leetcode.com/problems/predict-the-winner/
You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.

Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of 0. At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1. The player adds the chosen number to their score. The game ends when there are no more elements in the array.

Return true if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner, and you should also return true. You may assume that both players are playing optimally.

Example 1:

Input: nums = [1,5,2]
Output: false
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return false.
Example 2:

Input: nums = [1,5,233,7]
Output: true
Explanation: Player 1 first chooses 1. Then player 2 has to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.

Pattern: Minimax Backtracking
*/

/*
Time Complexity: O(2^n)
Space Complexity: O(n)
 */
class Solution {

    private boolean dfs(int[] nums, int left, int right, int p1Score, int p2Score, boolean p1Turn) {
        if (left > right)
            return p1Score >= p2Score;

        if (p1Turn) {
            // Player 1 tries to win
            return dfs(nums, left + 1, right, p1Score + nums[left], p2Score, false) ||
                    dfs(nums, left, right - 1, p1Score + nums[right], p2Score, false);
        } else {
            // Player 2 tries to stop Player 1
            return dfs(nums, left + 1, right, p1Score, p2Score + nums[left], true) &&
                    dfs(nums, left, right - 1, p1Score, p2Score + nums[right], true);
        }
    }

    public boolean predictTheWinner(int[] nums) {
        return dfs(nums, 0, nums.length - 1, 0, 0, true);
    }
}
// Player 1: needs at least one winning path → OR
// Player 2: must block all winning paths → AND

class Solution {

    private int dfs(int[] nums, int left, int right) {
        if (left == right)
            return nums[left];

        int pickLeft = nums[left] - dfs(nums, left + 1, right);
        int pickRight = nums[right] - dfs(nums, left, right - 1);

        return Math.max(pickLeft, pickRight);
    }

    public boolean predictTheWinner(int[] nums) {
        return dfs(nums, 0, nums.length - 1) >= 0;
    }
}
/*
 * At every step:
 * Current player chooses left or right
 * Opponent then plays optimally on remaining array
 * We try both choices and pick the one that gives us the best outcome.
 */