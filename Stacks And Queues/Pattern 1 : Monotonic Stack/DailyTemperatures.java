/* https://leetcode.com/problems/daily-temperatures/

Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]
*/

//TC - O(n) SC - O(n)
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];

        Stack<Integer> st = new Stack<>(); // stores indices of future days

        // Traverse from right so future warmer days are already processed
        for (int i = n - 1; i >= 0; i--) {

            // Remove all indices whose temperature is <= current
            // They cannot be the next warmer day for current or any previous element
            while (st.size() > 0 && temperatures[st.peek()] <= temperatures[i]) {
                st.pop();
            }

            // If stack is empty → no warmer day exists
            // Else → next warmer day distance = index difference
            if (st.size() == 0)
                ans[i] = 0;
            else
                ans[i] = st.peek() - i;

            // Push current index to serve as a candidate for previous days
            st.push(i);
        }

        return ans;
    }
}