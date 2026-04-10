/* Problem Discription: https://leetcode.com/problems/longest-substring-without-repeating-characters/
Given a string s, find the length of the longest substring without duplicate characters.
Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Pattern : Sliding Window , Last Seen Index Approach
 */
/*
* Solution 1 : Sliding Window Method
* right expands window by adding characters
* If a character repeats → shrink from left until window becomes valid
* Update max length when window has all unique characters
* Time: O(n) (each character added & removed once)
* Space: O(min(n, charset))
 */
class Solution {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> freq = new HashMap<>();

        int left = 0, right = 0;
        int maxLength = 0;

        while (right < s.length()) {
            char ch = s.charAt(right);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            // shrink window until no duplicates
            while (freq.get(ch) > 1) {
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);

                if (freq.get(leftChar) == 0)
                    freq.remove(leftChar);

                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }
        return maxLength;
    }
}

/*
 * Solution 2 (Optimised Last Seen Index)
 * When we see a character again at index right:
 * If it was last seen inside the current window
 * Then the window becomes invalid
 * 
 * So we do: left = lastSeen[char] + 1
 * But only if: lastSeen[char] >= left
 */
class Solution {

    public int lengthOfLongestSubstring(String s) {
        int[] lastSeen = new int[128];
        Arrays.fill(lastSeen, -1);

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            if (lastSeen[ch] >= left) {
                left = lastSeen[ch] + 1;
            }

            lastSeen[ch] = right;
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}