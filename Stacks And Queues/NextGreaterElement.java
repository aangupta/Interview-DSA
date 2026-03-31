/*
https://takeuforward.org/plus/dsa/problems/next-greater-element?subject=dsa&approach=optimal

Given an array arr of size n containing elements, find the next greater element for each element in the array in the order of their appearance.



The next greater element of an element in the array is the nearest element on the right that is greater than the current element.



If there does not exist a next greater element for the current element, then the next greater element for that element is -1.


Example 1

Input: arr = [1, 3, 2, 4]

Output: [3, 4, 4, -1]

Explanation: In the array, the next larger element to 1 is 3, 3 is 4, 2 is 4 and for 4 is -1, since it does not exist.

*/
class Solution {
    public int[] nextLargerElement(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();

        int[] result = new int[n];

        // Last element has no element to its right → always -1
        st.push(arr[n - 1]);
        result[n - 1] = -1;

        // Traverse from right → left
        for (int i = n - 2; i >= 0; i--) {

            // Case 1: Immediate greater element found at top
            if (arr[i] < st.peek()) {
                result[i] = st.peek();
            } else {

                // Pop all elements <= current element
                // because they can never be next greater
                while (st.size() != 0 && st.peek() <= arr[i]) {
                    st.pop();
                }

                // If stack becomes empty → no greater element
                if (st.size() == 0)
                    result[i] = -1;

                // Else top is next greater element
                else
                    result[i] = st.peek();

            }

            // Push current element for future comparisons
            st.push(arr[i]);
        }

        return result;
    }
}