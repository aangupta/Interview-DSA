/*
Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation: 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444

*/

class Solution {

    private int[] findNextSmallest(int[] arr) {
        int n = arr.length;

        int[] ans = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            ans[i] = (st.isEmpty() ? n : st.peek());
            st.push(i);
        }

        return ans;
    }

    private int[] findPrevSmallestEqual(int[] arr) {
        int n = arr.length;

        int[] ans = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            ans[i] = (st.isEmpty() ? -1 : st.peek());
            st.push(i);
        }

        return ans;

    }

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] nextSmallest = findNextSmallest(arr);
        int[] prevSmallestEqual = findPrevSmallestEqual(arr);

        int sum = 0;
        int mod = (int) 1e9 + 7;

        for (int i = 0; i < n; i++) {

            int leftChoices = i - prevSmallestEqual[i];
            int rightChoices = nextSmallest[i] - i;

            long freq = leftChoices * rightChoices * 1L;

            int contribution = (int) ((freq * arr[i]) % mod);

            sum = (sum + contribution) % mod;
        }

        return sum;

    }
}
