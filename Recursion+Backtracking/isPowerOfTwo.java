/*Problem: https://leetcode.com/problems/power-of-two/
Pattern: Basic Recursion

Time Complexity: O(n)
Space Complexity: O(n) - recursive stack space
*/

class Solution {

    public boolean isPowerOfTwo(int n) {
        if (n == 0)
            return false;
        if (n == 1)
            return true;

        if (n % 2 != 0)
            return false;

        return isPowerOfTwo(n / 2);
    }
}

/*
 * Common Mistake:
 * - Did not handle the edge case when n == 0, which caused a runtime error.
 */
