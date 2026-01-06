/*Problem: https://www.geeksforgeeks.org/problems/key-pair5616/1
Pattern: Two Pointer

Time Complexity: O(n) + O(nlogn)
Space Complexity: O(1)
*/

class Solution {
    boolean twoSum(int arr[], int target) {
        // code here

        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target)
                return true;
            else if (sum > target)
                right--;
            else
                left++;
        }
        return false;
    }
}