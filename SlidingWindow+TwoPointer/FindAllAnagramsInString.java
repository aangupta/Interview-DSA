/* Problem Link: https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

Pattern : Sliding Window
*/

//Brute-Force Solution TC - O(n^2) SC - O(26)
class Solution {

    private boolean allZeros(int[] hash) {
        for (int i = 0; i < 26; i++) {
            if (hash[i] != 0)
                return false;
        }
        return true;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        for (int left = 0; left < s.length(); left++) {
            int count = 0;
            int[] hash = new int[26];
            for (char ch : p.toCharArray()) {
                hash[ch - 'a']++;
            }

            for (int right = left; right < s.length(); right++) {
                char ch = s.charAt(right);
                hash[ch - 'a']--;

                if (allZeros(hash)) {
                    result.add(left);
                    break;
                }
            }
        }
        return result;
    }
}

// Optimised Solution TC - O(n + m + 26) SC - O(26)
class Solution {

    private boolean allZeros(int[] hash) {
        for (int i = 0; i < 26; i++) {
            if (hash[i] != 0)
                return false;
        }
        return true;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] hash = new int[26];

        for (char ch : p.toCharArray()) {
            hash[ch - 'a']++;
        }

        int left = 0, right = 0;

        while (right < s.length()) {
            char ch = s.charAt(right);
            hash[ch - 'a']--;

            if (right - left + 1 == p.length()) {
                if (allZeros(hash))
                    result.add(left);
                hash[s.charAt(left) - 'a']++;
                left++;
            }
            right++;
        }

        return result;
    }
}

// Without AllZeros function
class Solution {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] hash = new int[26];

        for (char ch : p.toCharArray()) {
            hash[ch - 'a']++;
        }

        int left = 0, right = 0;
        int count = p.length();

        while (right < s.length()) {
            char ch = s.charAt(right);

            if (hash[ch - 'a'] > 0)
                count--;
            hash[ch - 'a']--;

            if (right - left + 1 == p.length()) {
                if (count == 0)
                    result.add(left);

                char leftChar = s.charAt(left);

                if (hash[leftChar - 'a'] >= 0)
                    count++;

                hash[leftChar - 'a']++;
                left++;
            }
            right++;
        }

        return result;
    }
}
