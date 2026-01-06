/*Problem: https://www.geeksforgeeks.org/problems/pair-with-given-sum-in-a-sorted-array4940/1

You are given an integer target and an array arr[]. You have to find number of pairs in arr[] which sums up to target. It is given that the elements of the arr[] are in sorted order.
Note: pairs should have elements of distinct indexes. 

Examples :

Input: arr[] = [-1, 1, 5, 5, 7], target = 6
Output: 3
Explanation: There are 3 pairs which sum up to 6 : {1, 5}, {1, 5} and {-1, 7}.
Input: arr[] = [1, 1, 1, 1], target = 2
Output: 6
Explanation: There are 6 pairs which sum up to 2 : {1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1} and {1, 1}.


Pattern: Two Pointer
*/
/*
* TC - O(n2)
* SC - O(1)
 */
class Solution {

    int countPairs(int arr[], int target) {
        // Complete the function
        int count = 0;
        int n = arr.length;

        for (int i = 0; i <= n - 2; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                if (arr[i] + arr[j] == target)
                    count++;
            }
        }
        return count;
    }
}

/* Optimised Solution */

/*
 * Move pointers based on sum comparison
 * For sum match:
 * Same numbers → nC2
 * Different numbers → freqLeft × freqRight
 * TC - O(n)
 * SC - O(1)
 */

class Solution {

    int countPairs(int arr[], int target) {
        // Complete the function
        int count = 0;

        int left = 0, right = arr.length - 1;

        while (left < right) {
            if (arr[left] + arr[right] < target) {
                left++;
            } else if (arr[left] + arr[right] > target) {
                right--;
            } else {
                // arr[left] + arr[right] = target

                // arr[left] == arr[right]
                if (arr[left] == arr[right]) {
                    int n = right - left + 1;
                    count += n * (n - 1) / 2;
                    break;
                }

                // arr[left] != arr[right]
                int leftVal = arr[left];
                int leftCount = 0;

                // Count duplicates on left
                while (left <= right && arr[left] == leftVal) {
                    leftCount++;
                    left++;
                }

                // Count duplicates on right
                int rightVal = arr[right];
                int rightCount = 0;
                while (left <= right && arr[right] == rightVal) {
                    rightCount++;
                    right--;
                }

                count += leftCount * rightCount;

            }
        }
        return count;
    }
}