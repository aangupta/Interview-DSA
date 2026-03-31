/*Given a circular integer array arr, return the next greater element for every element in arr.

The next greater element for an element x is the first element greater than x that we come across while traversing the array in a clockwise manner.

If it doesn't exist, return -1 for that element.

Example 1
Input: arr = [3, 10, 4, 2, 1, 2, 6, 1, 7, 2, 9]
Output: [10, -1, 6, 6, 2, 6, 7, 7, 9, 9, 10]
Explanation:
For the first element in arr i.e, 3, the greater element which comes next to it while traversing and is closest to it is 10. Hence,10 is present on index 0 in the resultant array. Now for the second element i.e, 10, there is no greater number and hence -1 is it’s next greater element (NGE). Similarly, we got the NGEs for all other elements present in arr.  */

//Approach 1: Brute Force TC - O(n^2) SC - O(n)
class Solution {
    public int[] nextGreaterElements(int[] arr) {
        int n = arr.length;

        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        for (int i = 0; i < n; i++) {

            int currEle = arr[i];

            // Check next elements in circular manner
            // We go up to n-1 steps ahead to cover full circular traversal
            for (int j = i + 1; j < i + n - 1; j++) {

                int ind = j % n; // circular index

                // If we find a greater element, store it and break
                if (arr[ind] > currEle) {
                    ans[i] = arr[ind];
                    break;
                }
            }
        }
        return ans;
    }
}

// Aprroach 2: Optimal
class Solution {
    public int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse twice (simulate circular array)
        for (int i = 2 * n - 1; i >= 0; i--) {

            int ind = i % n; // circular index

            // Maintain decreasing stack:
            // Remove all elements <= current, as they can never be next greater
            while (st.size() > 0 && st.peek() <= arr[ind]) {
                st.pop();
            }

            // Only fill answer in first pass (i < n)
            if (i < n) {
                // If stack empty → no greater element
                // Else → top is next greater element
                if (st.size() == 0)
                    ans[i] = -1;
                else
                    ans[i] = st.peek();
            }

            // Push current element for future comparisons
            st.push(arr[ind]);
        }

        return ans;
    }
}
