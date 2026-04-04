/*
Given an array arr of size n, where each element arr[i] represents the stock price on day i. Calculate the span of stock prices for each day.
The span Si for a specific day i is defined as the maximum number of consecutive previous days (including the current day) for which the stock price was less than or equal to the price on day i.

Input: n = 6, arr = [15, 13, 12, 14, 16, 20]

Output: [1, 1, 1, 3, 5, 6]

Explanation:

Traversing the given input span:

15 is greater than or equal to 15 and there are no more elements behind it, so the span is 1.

13 is smaller than 15, so the span is 1.

12 is smaller than 13, so the span is 1.

14 is greater than or equal to 12 and 13, but smaller than 15, so the span is 3 (days with values 12, 13, and 14).

16 is greater than or equal to 14, 12, 13, and 15, so the span is 5.

20 is greater than or equal to all previous elements, so the span is 6.

Hence the output will be 1 1 1 3 5 6.

*/

class Solution {
    public int[] stockSpan(int[] arr, int n) {
        int[] ans = new int[n]; // final span values
        int[] pge = new int[n]; // previous greater element index
        Stack<Integer> stack = new Stack<>(); // monotonic decreasing stack (indices)

        for (int i = 0; i < n; i++) {

            // pop all smaller or equal elements → not useful for span
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }

            // if empty → no greater on left → boundary = -1
            // else → top is previous greater element index
            pge[i] = (stack.isEmpty() ? -1 : stack.peek());

            // push current index
            stack.push(i);
        }

        // span = distance from previous greater element
        for (int i = 0; i < n; i++) {
            ans[i] = i - pge[i];
        }

        return ans;
    }
}