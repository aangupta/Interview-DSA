/*Problem: https://leetcode.com/problems/fibonacci-number/
Pattern: Basic Recursion

Time Complexity: O(n)
Space Complexity: O(n) - recursive stack space
*/

class Solution {
    public int fib(int n) {
        if (n <= 1)
            return n;
        return fib(n - 1) + fib(n - 2);
    }
}
/*
 * Common Mistake:
 * - In the base condition returned 1 instead of n - causing the wrong answer
 */