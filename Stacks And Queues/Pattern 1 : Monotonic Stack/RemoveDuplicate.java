/*
https://leetcode.com/problems/remove-duplicate-letters/description/

Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

 

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"
 

Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.
 

Similar Problem:- https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 
*/

//Time: O(n) 
//Space: O(1) (since max 26 characters)
//If each element is pushed and popped at most once → O(n)
class Solution {

    public String removeDuplicateLetters(String s) {
        int[] freq = new int[26]; // stores remaining occurrences of each char

        boolean[] visited = new boolean[26]; // tracks if char already in result

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++; // count frequency
        }

        Stack<Character> stack = new Stack<>(); // monotonic increasing stack

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']--; // current char is now used

            if (visited[ch - 'a'])
                continue; // skip if already in stack

            // remove bigger chars if they can appear later again
            while (!stack.isEmpty() && stack.peek() > ch && freq[stack.peek() - 'a'] > 0) {
                char temp = stack.pop();
                visited[temp - 'a'] = false; // mark as not in stack
            }

            stack.push(ch); // add current char
            visited[ch - 'a'] = true;
        }

        // build result from stack (already in correct order)
        StringBuilder sb = new StringBuilder();
        for (char c : stack)
            sb.append(c);

        return sb.toString();
    }
}