/*Problem Discription: https://leetcode.com/problems/find-the-duplicate-number/

Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and using only constant extra space.

Example 1:

Input: nums = [1,3,4,2,2]
Output: 2
Example 2:

Input: nums = [3,1,3,4,2]
Output: 3
Example 3:

Input: nums = [3,3,3,3,3]
Output: 3

Constraints:

1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.


Pattern : Two Pointer, Binary Serach, Hare and Tortoise 
*/

/* Solution 1  
* Using freq array
* TC - O(n)
* SC - O(n)
 */
class Solution {

    public int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        int[] freq = new int[n + 1];

        for (int num : nums) {
            freq[num]++;
        }
        int ans = -1;
        for (int i = 1; i <= n; i++) {
            if (freq[i] >= 2) {
                ans = i;
                break;
            }
        }

        return ans;
    }
}

/*
 * Solution 2
 * Binary Search
 * TC - O(nlogn)
 * SC - O(1)
 */
class Solution {
    private int countLessEqual(int[] nums, int mid) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= mid)
                count++;
        }
        return count;
    }

    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int low = 1, high = n;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (countLessEqual(nums, mid) > mid) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}

/*
 * Solution 3 Optimised Approach
 * Hare and Tortoise Method / Floyd’s Cycle Detection
 * TC - O(n)
 * SC - O(1)
 */

class Solution {

    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        slow = nums[slow];
        fast = nums[nums[fast]];

        // Phase 1 : Detect Cycle
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // Phase 2 : Finding Cycle Entry Point (Duplicate number)
        slow = nums[0];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
