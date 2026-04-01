/*
https://leetcode.com/problems/first-matching-character-from-both-ends/

Example 1:

Input: s = "abcacbd"

Output: 1

Explanation:

At index i = 1, s[1] and s[5] are both 'b'.

No smaller index satisfies the condition, so the answer is 1.

Example 2:

Input: s = "abc"

Output: 1

Explanation:

​​​​​​​At index i = 1, the two compared positions coincide, so both characters are 'b'.

No smaller index satisfies the condition, so the answer is 1.

Example 3:

Input: s = "abcdab"

Output: -1

Explanation:

​​​​​​​For every index i, the characters at positions i and n - i - 1 are different.

Therefore, no valid index exists, so the answer is -1.

*/

class Solution {
    public int firstMatchingIndex(String s) {
        int smallestIdx = -1;

        int len = s.length();

        for (int i = 0; i <= len / 2; i++) {
            if (s.charAt(i) == s.charAt(len - 1 - i)) {
                smallestIdx = i;
                break;
            }
        }

        return (smallestIdx != -1 ? smallestIdx : -1);

    }
}