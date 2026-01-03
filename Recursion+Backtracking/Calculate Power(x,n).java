/*Problem: https://leetcode.com/problems/powx-n/description/
Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
Pattern: Basic Recursion

Time Complexity: O(log n)
Space Complexity: O(log n) - recursive stack space
*/

/* Submission 1 - 
** gave Stack Overflow error for x =1.00000 n = -2147483648(Integer.MIN_VALUE) 
** x * myPowUtils(x, n - 1) - This can degrade to O(n) for odd values.
*/

class Solution {

    private double myPowUtils(double x, int n) {
        if (n == 0)
            return 1;

        if (n == 1)
            return x;

        // n is even
        if (n % 2 == 0) {
            double val = myPowUtils(x, n / 2);
            return val * val;
        }

        return x * myPowUtils(x, n - 1); // This can degrade to O(n) for odd values.
    }

    public double myPow(double x, int n) {
        // handling -ve sign
        int sign = 0;
        if (n < 0) {
            sign = 1;
            n = n * (-1);
        }

        double ans = myPowUtils(x, n);

        return (sign == 1 ? 1 / ans : ans);
    }
}

/* Submission 2 - solving the problems in above solution */

class Solution {

    private double myPowUtils(double x, long n) {
        if (n == 0)
            return 1;

        if (n == 1)
            return x;

        double half = myPowUtils(x, n / 2);

        return (n % 2 == 0 ? half * half : x * half * half);
    }

    public double myPow(double x, int n) {
        long N = n; // prevent overflow

        if (N < 0) {
            N = -N;
            x = 1 / x;
        }

        return myPowUtils(x, N);
    }
}

/* Iterative Approach */

class Solution {

    public double myPow(double x, int n) {
        double ans = 1; // To store the result
        long nn = n; // Use long to avoid overflow with large negative n

        // If n is negative, convert it to positive for easier calculation
        if (nn < 0) {
            nn = -nn;
        }

        // Loop until nn becomes 0
        while (nn > 0) {
            // If nn is odd, multiply the result with current x
            if (nn % 2 == 1) {
                ans *= x;
                nn--; // Reduce nn by 1 if it's odd
            } else {
                x *= x; // Square the base
                nn /= 2; // Divide nn by 2
            }
        }

        // If n is negative, return the reciprocal of the result
        if (n < 0) {
            ans = 1 / ans;
        }

        return ans;
    }
}

/*
 * OVERFLOW CASES
 * Rule 1: Never negate Integer.MIN_VALUE
 * Rule 2: Overflow happens during computation, not assignment
 * If ANY is yes → convert to long FIRST
 */
