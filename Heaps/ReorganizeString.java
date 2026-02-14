/* https://leetcode.com/problems/reorganize-string/description/
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.

Example 1:

Input: s = "aab"
Output: "aba"
Example 2:

Input: s = "aaab"
Output: ""
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 */

//Approach 1: Using MaxHeap TC - O(nlogk)
class Pair {
    char ch;
    int freq;

    Pair(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}

class Solution {

    public String reorganizeString(String s) {
        int n = s.length();
        if (n == 1)
            return s;

        Map<Character, Integer> map = new HashMap<>();

        int maxFreq = 0;
        for (char ch : s.toCharArray()) {
            int freq = map.getOrDefault(ch, 0) + 1;
            map.put(ch, freq);
            maxFreq = Math.max(maxFreq, freq);
        }

        if (maxFreq > (n + 1) / 2)
            return "";

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.freq, a.freq));

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            maxHeap.add(new Pair(entry.getKey(), entry.getValue()));
        }

        StringBuilder sb = new StringBuilder();

        while (maxHeap.size() > 1) {
            Pair p1 = maxHeap.poll();
            Pair p2 = maxHeap.poll();
            // System.out.println(p1.ch +" "+p2.ch);

            sb.append(p1.ch);
            p1.freq = p1.freq - 1;
            sb.append(p2.ch);
            p2.freq = p2.freq - 1;

            if (p1.freq > 0) {
                maxHeap.add(p1);
            }

            if (p2.freq > 0) {
                maxHeap.add(p2);
            }
        }

        if (!maxHeap.isEmpty()) {
            Pair last = maxHeap.poll();
            if (last.freq > 1)
                return "";
            sb.append(last.ch);
        }

        return sb.toString();
    }
}
/*
 * The most frequent character must fit into alternating slots.
 * Number of alternating slots = (n + 1) / 2.
 * If it exceeds that → impossible.
 */

// Approach 2: Even Index Filling Algorithm (Greedy) TC - O(n)
class Solution {

    public String reorganizeString(String s) {
        int n = s.length();
        if (n == 1)
            return s;

        Map<Character, Integer> map = new HashMap<>();

        int maxFreq = 0;
        char maxChar = ' ';

        for (char ch : s.toCharArray()) {
            int freq = map.getOrDefault(ch, 0) + 1;
            map.put(ch, freq);

            if (freq > maxFreq) {
                maxFreq = freq;
                maxChar = ch;
            }
        }

        if (maxFreq > (n + 1) / 2)
            return "";

        char[] result = new char[n];
        int index = 0;

        // Fill even positions with most frequent char
        while (map.get(maxChar) > 0) {
            result[index] = maxChar;
            index += 2;
            map.put(maxChar, map.get(maxChar) - 1);
        }

        // Fill remaining characters
        for (char ch : map.keySet()) {
            if (ch == maxChar)
                continue;

            while (map.get(ch) > 0) {
                if (index >= n) {
                    index = 1; // switch to odd index
                }

                result[index] = ch;
                index += 2;
                map.put(ch, map.get(ch) - 1);
            }
        }

        return new String(result);
    }
}

/*
 * use char array instead of stringbuilder
 * while filling the remaing char if the current char is equals to ma max Char -
 * continue;
 * sb.setCharAt(index, ch) - will only work iif there is any char present at
 * that index - here the stringbuilder is empty - so using the char array
 */
