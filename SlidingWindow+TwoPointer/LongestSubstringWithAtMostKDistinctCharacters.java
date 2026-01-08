/* Problem Description: https://takeuforward.org/plus/dsa/problems/longest-substring-with-at-most-k-distinct-characters?tab=description 

Given a string s and an integer k.Find the length of the longest substring with at most k distinct characters.


Example 1

Input : s = "aababbcaacc" , k = 2

Output : 6

Explanation : The longest substring with at most two distinct characters is "aababb".

The length of the string 6.

Example 2

Input : s = "abcddefg" , k = 3

Output : 4

Explanation : The longest substring with at most three distinct characters is "bcdd".

The length of the string 4.

Example 3

Input : s = "abccab" , k = 4

Output:

6

Pattern : Slidinh Window
*/
//TC - O(n)
//SC - O(k) because the HashMap stores frequency counts for at most k + 1 distinct characters at any point in time.
class Solution {
    public int kDistinctChar(String s, int k) {
        // your code goes here
        Map<Character, Integer> freq = new HashMap<>();
        int left = 0, right = 0;
        int maxLen = 0;

        while (right < s.length()) {
            char ch = s.charAt(right);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            while (freq.size() > k) {
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);

                if (freq.get(leftChar) == 0) {
                    freq.remove(leftChar);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}
