/*
https://leetcode.com/problems/gray-code/

An n-bit gray code sequence is a sequence of 2n integers where:

Every integer is in the inclusive range [0, 2n - 1],
The first integer is 0,
An integer appears no more than once in the sequence,
The binary representation of every pair of adjacent integers differs by exactly one bit, and
The binary representation of the first and last integers differs by exactly one bit.
Given an integer n, return any valid n-bit gray code sequence.

Example 1:

Input: n = 2
Output: [0,1,3,2]
Explanation:
The binary representation of [0,1,3,2] is [00,01,11,10].
- 00 and 01 differ by one bit
- 01 and 11 differ by one bit
- 11 and 10 differ by one bit
- 10 and 00 differ by one bit
[0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
- 00 and 10 differ by one bit
- 10 and 11 differ by one bit
- 11 and 01 differ by one bit
- 01 and 00 differ by one bit

*/
//Approach 1 :  Reflection Method
// TC - O(2^n) SC - O(2 ^n)
class Solution {

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();

        // Start with 0 for n = 0
        result.add(0);

        // Iterate over each bit position (0 to n-1)
        for (int i = 0; i < n; i++) {
            int size = result.size();

            // Traverse the existing list in reverse order
            // This ensures only one bit changes between adjacent numbers
            for (int j = size - 1; j >= 0; j--) {
                // Add new numbers by setting the ith bit
                // (1 << i) creates a number with only ith bit set
                // OR operation sets that bit in the current number
                result.add(result.get(j) | (1 << i));
            }
        }

        return result;
    }
}

// Approach 2 : Bit Manipulation
// TC - O(2^n) SC - O(2 ^n)
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();

        // Total numbers in Gray code sequence = 2^n
        int total = 1 << n; // left shift → 2^n

        // Generate each number from 0 to (2^n - 1)
        for (int i = 0; i < total; i++) {

            // Convert normal binary number to Gray code
            // i >> 1 shifts bits right (i/2)
            // XOR gives difference between current and shifted bits
            result.add(i ^ (i >> 1));
        }

        return result;
    }
}