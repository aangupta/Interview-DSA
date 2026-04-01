/* https://leetcode.com/problems/xor-queries-of-a-subarray/

You are given an array arr of positive integers. You are also given the array queries where queries[i] = [lefti, righti].

For each query i compute the XOR of elements from lefti to righti (that is, arr[lefti] XOR arr[lefti + 1] XOR ... XOR arr[righti] ).

Return an array answer where answer[i] is the answer to the ith query.

Example 1:

Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
Output: [2,7,14,8] 
Explanation: 
The binary representation of the elements in the array are:
1 = 0001 
3 = 0011 
4 = 0100 
8 = 1000 
The XOR values for queries are:
[0,1] = 1 xor 3 = 2 
[1,2] = 3 xor 4 = 7 
[0,3] = 1 xor 3 xor 4 xor 8 = 14 
[3,3] = 8


*/

class Solution {

    public int[] xorQueries(int[] arr, int[][] queries) {
        // Result array to store answers for each query
        int[] ans = new int[queries.length];

        // Prefix XOR array where prefix[i] = XOR of elements from index 0 to i
        int[] prefix = new int[arr.length];

        // Base case: first element
        prefix[0] = arr[0];

        // Build prefix XOR array
        // prefix[i] = arr[0] ^ arr[1] ^ ... ^ arr[i]
        for (int i = 1; i < arr.length; i++) {
            prefix[i] = prefix[i - 1] ^ arr[i];
        }

        // Process each query
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];

            // If query starts from index 0
            // XOR from 0 to right = prefix[right]
            if (left == 0) {
                ans[i] = prefix[right];
            } else {
                // prefix[right] = arr[0] ^ ... ^ arr[left-1] ^ arr[left] ^ ... ^ arr[right]
                // prefix[left-1] = arr[0] ^ ... ^ arr[left-1]
                // XOR cancels common part → gives arr[left] ^ ... ^ arr[right]
                ans[i] = prefix[right] ^ prefix[left - 1];
            }
        }

        return ans;
    }
}