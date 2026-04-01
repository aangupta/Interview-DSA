/*
https://leetcode.com/problems/divide-two-integers/

Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

Return the quotient after dividing dividend by divisor.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.

 Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = 3.33333.. which is truncated to 3.
*/

//Time Complexity = O((log n) × (log n)) = O(log² n)
class Solution {

    public int divide(int dividend, int divisor) {
        // Edge case: same numbers → answer is 1
        if (dividend == divisor)
            return 1;

        int sign = 1;

        // Determine sign of result
        // If one is negative and the other is positive → result is negative
        if ((dividend >= 0 && divisor < 0) || (dividend <= 0 && divisor > 0)) {
            sign = -1;
        }

        // Convert to long to avoid overflow (important for INT_MIN)
        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long quotient = 0;

        // Keep subtracting divisor multiples from dividend
        while (n >= d) {
            int count = 0;

            // Find the largest power such that (d * 2^(count+1)) <= n
            // i.e., keep doubling divisor until it exceeds n
            // We use (count + 1) because: We are checking the next doubling to find the
            // maximum safe power.”
            while (n >= (d << (count + 1))) {
                count++;
            }

            // Add the corresponding multiple (2^count) to quotient
            quotient += (1L << count);

            // Subtract that chunk from n
            n -= (d << count);
        }

        // Handle overflow case: result exceeds Integer range
        if (quotient == (1L << 31) && sign == 1)
            return Integer.MAX_VALUE;
        if (quotient == (1L << 31) && sign == -1)
            return Integer.MIN_VALUE;

        return sign == 1 ? (int) quotient : -(int) quotient;
    }
}
