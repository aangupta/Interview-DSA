/*Problem: https://leetcode.com/problems/reverse-string/
Pattern: Basic Recursion, Two Pointer

Time Complexity: O(n)
Space Complexity: O(n) - recursive stack space
*/

class Solution {

    private void reverseString(int start, int end, char[] s) {

        if (start >= end)
            return;
        // start == end → middle reached (odd length)
        // start > end → crossed over (even length)

        char temp = s[start];
        s[start] = s[end];
        s[end] = temp;

        reverseString(start + 1, end - 1, s);
    }

    public void reverseString(char[] s) {
        int n = s.length;
        reverseString(0, n - 1, s);
    }
}

/*
 * Common Mistake:
 * - in the base case only checking when start == end
 */