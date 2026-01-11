/*
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

 

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.

Pattern : Sliding Window + Frequency Array
*/
//TC - O(N + N + M)
//SC - O(256)
class Solution {

    public String minWindow(String s, String t) {

        int[] freq = new int[256];

        int left = 0, right = 0;
        int sIndex = -1;
        int minLen = Integer.MAX_VALUE;
        int count = 0;

        for (char ch : t.toCharArray()) {
            freq[ch]++;
        }

        while (right < s.length()) {
            char ch = s.charAt(right);

            if (freq[ch] > 0)
                count++;
            freq[ch]--;

            while (count == tLen) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    sIndex = left;
                }
                char leftChar = s.charAt(left);
                freq[leftChar]++;

                if (freq[leftChar] > 0)
                    count--;
                left++;
            }
            right++;
        }

        return (sIndex == -1 ? "" : s.substring(sIndex, sIndex + minLen));
    }
}
