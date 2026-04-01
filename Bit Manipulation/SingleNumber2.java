/*
Given an array nums where each integer in nums appears thrice except one. Find out the number that has appeared only once.

Example 1
Input : nums = [2, 2, 2, 3]
Output : 3


Example 2
Input : nums = [1, 0, 3, 0, 1, 1, 3, 3, 10, 0]
Output : 10
*/

//Better I
/*
* Count set bits at each position across all numbers.
* Since every number appears thrice, bits cancel out in modulo 3.
* Remaining bits (count % 3 = 1) form the unique number.
* Time: O(32 * n) ≈ O(n)
* Space: O(1)
* Works even for negative numbers (important edge case)
*/
class Solution {
    public int singleNumber(int[] nums) {

        int ans = 0; // Stores the final unique number

        // Iterate over all 32 bit positions
        for (int bitIndex = 0; bitIndex < 32; bitIndex++) {

            int count = 0;

            // Count how many numbers have the current bit set
            for (int num : nums) {
                if ((num & (1 << bitIndex)) != 0) {
                    count++;
                }
            }

            // If count % 3 == 1, this bit belongs to the unique number
            // because all other numbers contribute in multiples of 3
            if (count % 3 == 1) {
                ans = ans | (1 << bitIndex); // Set this bit in result
            }
        }

        return ans;
    }
}

// Better II
/*
 * Sort the array so that elements appearing thrice are grouped together.
 * Traverse in steps of 3 and compare adjacent elements to detect the mismatch.
 * The element that breaks the pattern is the unique number.
 * Time: O(n log n + n/3)
 * Space: O(1)
 */
class Solution {
    public int singleNumber(int[] nums) {

        // Step 1: Sort the array so that duplicates come together
        Arrays.sort(nums);

        // Step 2: Traverse in steps of 3 (since every element appears thrice except
        // one)
        for (int i = 1; i < nums.length; i = i + 3) {

            // Step 3: Compare current element with previous
            // If they are not equal → previous element is the unique one
            if (nums[i] != nums[i - 1]) {
                return nums[i - 1];
            }
        }

        // Step 4: If all groups were valid, the unique element is at the end
        return nums[nums.length - 1];
    }
}