/*
https://leetcode.com/problems/top-k-frequent-words/description/

Given an array of strings words and an integer k, return the k most frequent strings.

Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.

Example 1:

Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:

Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.

*/

class Solution {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freq = new HashMap<>();

        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>(
                (w1, w2) -> {
                    int f1 = freq.get(w1);
                    int f2 = freq.get(w2);
                    if (f1 != f2)
                        return Integer.compare(f1, f2); // smaller freq first
                    return w2.compareTo(w1); // lexicographically larger first
                });

        for (String word : freq.keySet()) {
            minHeap.add(word);
            if (minHeap.size() > k)
                minHeap.poll();
        }

        List<String> result = new ArrayList<>();

        while (minHeap.size() > 0) {
            result.add(minHeap.poll());
        }

        Collections.reverse(result);

        return result;
    }
}
/*
 * So in heap:
 * Smaller frequency ⇒ lower priority
 * Same frequency but lexicographically larger ⇒ also lower priority
 */

/*
 * Comparator Notes (Top K Frequent Words)
 * Heap Type - We use Min Heap of size k
 * Root = “worst” element among top k
 * A word is worse if: Lower frequency OR same frequency but lexicographically
 * larger Because: We want highest frequency If tie → lexicographically smaller
 * first
 * Comparator Logic
 * (w1, w2) -> {
 * if (freq.get(w1) != freq.get(w2))
 * return Integer.compare(freq.get(w1), freq.get(w2));
 * return w2.compareTo(w1);
 * }
 * 
 * Why w2.compareTo(w1)?
 * Reverses lexicographical order
 * Makes lexicographically larger word “smaller” in heap
 * So it gets removed first
 * Leaving lexicographically smaller word in final result
 * freq(i) = 2
 * freq(love) = 2
 * Tie → w2.compareTo(w1) love will be move from heap
 */